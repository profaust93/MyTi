package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 16.21.3.
 */
public class TimeLapsDAOImpl extends DAOImpl implements TimeLapsDAO {
    @Override
    public void create(TimeLaps timeLaps) throws DBException {
        if(timeLaps == null){
            return;
        }

        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO my_ti.TimeLaps VALUES (DEFAULT,?,?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            if(timeLaps.getCompleteTime() != null){
                preparedStatement.setTimestamp(1, Timestamp.valueOf(timeLaps.getCompleteTime()));
            } else {
                preparedStatement.setNull(1,java.sql.Types.TIMESTAMP);
            }
            preparedStatement.setLong(2,timeLaps.getUserId());
            preparedStatement.setString(3,timeLaps.getShortDescription());
            preparedStatement.setString(4,timeLaps.getLongDescription());
            preparedStatement.setString(5,timeLaps.getCategory());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                timeLaps.setTimeLapsId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(TimeLaps timeLaps) throws DBException {
        if(timeLaps == null){
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE my_ti.TimeLaps SET CompleteTime = ?," +
                            "ToDoID = ?, ShortDescription = ?, LongDescription = ?,CategoryId = ?");
            if(timeLaps.getCompleteTime() != null){
                preparedStatement.setTimestamp(1, Timestamp.valueOf(timeLaps.getCompleteTime()));
            } else {
                preparedStatement.setNull(1,java.sql.Types.TIMESTAMP);
            }
            preparedStatement.setLong(2,timeLaps.getUserId());
            preparedStatement.setString(3,timeLaps.getShortDescription());
            preparedStatement.setString(4,timeLaps.getLongDescription());
            preparedStatement.setString(5,timeLaps.getCategory());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                timeLaps.setTimeLapsId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(TimeLaps timeLaps) throws DBException {
        if(timeLaps==null){
            return;
        }
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM my_ti.TimeLaps WHERE TimeLapsId = ?");
            preparedStatement.setLong(1,timeLaps.getTimeLapsId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public TimeLaps getById(Long id) throws DBException {
        Connection connection= null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.TimeLaps WHERE TimeLapsId =?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            TimeLaps timeLaps;
            if(rs.next()){
                timeLaps = new TimeLaps();
                mapResultSetToObject(timeLaps,rs);
                return timeLaps;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<TimeLaps> getAllTimeLaps() throws DBException {
        List<TimeLaps> allTimeLaps = new ArrayList<>();
        Connection connection = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM my_ti.TimeLaps");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                TimeLaps timeLaps = new TimeLaps();
                mapResultSetToObject(timeLaps,rs);
                allTimeLaps.add(timeLaps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return allTimeLaps;
    }

    void mapResultSetToObject(TimeLaps timeLaps, ResultSet rs) throws SQLException{
        timeLaps.setTimeLapsId(rs.getLong(1));
        timeLaps.setCompleteTime(rs.getTimestamp(2).toLocalDateTime());
        timeLaps.setUserId(rs.getLong(3));
        timeLaps.setShortDescription(rs.getString(4));
        timeLaps.setLongDescription(rs.getString(5));
        timeLaps.setCategory(rs.getString(6));
    }
}
