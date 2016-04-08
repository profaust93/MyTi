package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ToDoDAO;
import lv.javaguru.java2.domain.ToDo;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ToDoDAOImpl extends DAOImpl implements ToDoDAO {

    private  static Logger log = Logger.getLogger(ToDo.class.getName());

    @Override
    public void create(ToDo toDo) throws DBException {
        if(toDo == null) {
            return;
        }
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO my_ti.ToDo VALUES (DEFAULT ,?,?,?,?,?,?,?,?,?)"
                            ,PreparedStatement.RETURN_GENERATED_KEYS);
            if(toDo.getToDoTime() != null) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(toDo.getToDoTime()));
            } else {
                preparedStatement.setNull(1, java.sql.Types.TIMESTAMP);
            }
            if(toDo.getDeadLineTime() != null) {
                preparedStatement.setTimestamp(2, Timestamp.valueOf(toDo.getDeadLineTime()));
            } else  {
                preparedStatement.setNull(2,java.sql.Types.TIMESTAMP);

            }
            preparedStatement.setString(3,toDo.getCategory().orElse(null));
            preparedStatement.setString(4,toDo.getShortDescription().orElse(""));
            preparedStatement.setString(5, toDo.getLongDescription().orElse(""));
            preparedStatement.setInt(6, toDo.getPriority());
            preparedStatement.setBoolean(7,toDo.getDone());
            preparedStatement.setString(8,toDo.getToDoName());
            preparedStatement.setLong(9,toDo.getUserId());

            //Inserting entity to Database
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                toDo.setToDoId(rs.getLong(1));
            }

        } catch (Exception e ) {
            log.log(Level.WARNING,"Database error: ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }


    @Override
    public ToDo getById(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.ToDo WHERE ToDoID = ?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            ToDo toDo;
            if(rs.next()) {
                toDo = new ToDo();
                mapResultSetToObject(toDo,rs);
                return toDo;
            }
        } catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to get Todo With id "+ id +": ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }


    @Override
    public void update(ToDo toDo) throws DBException {
        if (toDo == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE my_ti.ToDo SET ToDoTime = ?, DeadLineTime = ?," +
                            "Category = ?, ShortDesc = ?, FullDesc = ?, Priority = ?, IsDone = ?, " +
                            "Name = ?, UserId = ? WHERE ToDoID = ?");

            if(toDo.getToDoTime() != null) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(toDo.getToDoTime()));
            } else {
                preparedStatement.setNull(1, java.sql.Types.TIMESTAMP);
            }
            if(toDo.getDeadLineTime() != null) {
                preparedStatement.setTimestamp(2, Timestamp.valueOf(toDo.getDeadLineTime()));
            } else  {
                preparedStatement.setNull(2,java.sql.Types.TIMESTAMP);

            }
            preparedStatement.setString(3,toDo.getCategory().orElse(null));
            preparedStatement.setString(4,toDo.getShortDescription().orElse(""));
            preparedStatement.setString(5, toDo.getLongDescription().orElse(""));
            preparedStatement.setInt(6, toDo.getPriority());
            preparedStatement.setBoolean(7,toDo.getDone());
            preparedStatement.setString(8,toDo.getToDoName());
            preparedStatement.setLong(9,toDo.getUserId());
            preparedStatement.setLong(10,toDo.getToDoId());

            preparedStatement.executeUpdate();
        } catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to update Todo With id "+ toDo.getToDoId() +": ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(ToDo toDo) throws DBException {
        if (toDo == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM my_ti.ToDo WHERE ToDoID = ?");
            preparedStatement.setLong(1,toDo.getToDoId());
            preparedStatement.executeUpdate();
        } catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to delete Todo With id "+ toDo.getToDoId() +": ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public List<ToDo> getAllToDo() throws DBException {
        Connection connection = null;
        List<ToDo> allToDo = new ArrayList<>();

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM my_ti.ToDo");
            ResultSet rs = preparedStatement.executeQuery();

            // map result set to object and add to List
            while (rs.next()) {
                ToDo toDo = new ToDo();
                mapResultSetToObject(toDo,rs);
                allToDo.add(toDo);
            }
        }catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to get all Todo: ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return allToDo;
    }

    @Override
    public void delete(List<String> todoIdList) throws DBException {
        if (todoIdList.isEmpty()) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            for(String toDoId : todoIdList) {
                statement.addBatch("DELETE FROM my_ti.ToDo WHERE ToDoID = "+toDoId);
            }
            int[] result = statement.executeBatch();
            for (int oneRes : result) {
                if (oneRes == 0) {
                    throw new DBException("Delete Error");
                }
            }
            connection.commit();
        } catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to delete Todo List ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<ToDo> getToDoByUserId(Long userId) throws DBException {Connection connection = null;
        List<ToDo> toDoList = new ArrayList<>();

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM my_ti.ToDo WHERE UserId = ?");
            preparedStatement.setLong(1,userId);
            ResultSet rs = preparedStatement.executeQuery();

            // map result set to object and add to List
            while (rs.next()) {
                ToDo toDo = new ToDo();
                mapResultSetToObject(toDo,rs);
                toDoList.add(toDo);
            }
        }catch (Exception e ){
            log.log(Level.WARNING,"Database error trying to get all Todo: ",e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return toDoList;

    }

    private void mapResultSetToObject(ToDo toDo, ResultSet rs) throws SQLException {
        toDo.setToDoId(rs.getLong(1));
        toDo.setToDoTime(rs.getTimestamp(2).toLocalDateTime());
        toDo.setDeadLineTime(rs.getTimestamp(3).toLocalDateTime());
        toDo.setCategory(rs.getString(4));
        toDo.setShortDescription(rs.getString(5));
        toDo.setLongDescription(rs.getString(6));
        toDo.setPriority(rs.getInt(7));
        toDo.setDone(rs.getBoolean(8));
        toDo.setToDoName(rs.getString(9));
        toDo.setUserId(rs.getLong(10));
    }
}

