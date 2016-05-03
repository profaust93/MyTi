package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.ChallengeDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Challenge;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("JDBC_ChallengeDAO")
public class ChallengeDAOImpl extends DAOImpl implements ChallengeDAO {
    @Override
    public void create(Challenge challenge) throws DBException {
        if (challenge == null){
            return;
        }
        Connection connection = null;
        try{
          connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO my_ti.Challenge VALUES (DEFAULT,?,?,?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,challenge.getChallengeName());
            preparedStatement.setLong(2,challenge.getFromUserId());
            preparedStatement.setLong(3,challenge.getToUserId());
            preparedStatement.setString(4,challenge.getChallengeState());
            preparedStatement.setString(5,challenge.getDescription());
            if(challenge.getEndTime() != null){
                preparedStatement.setTimestamp(6, Timestamp.valueOf(challenge.getEndTime()));
            } else preparedStatement.setNull(6, Types.TIMESTAMP);

            preparedStatement.executeUpdate();

            ResultSet rs =preparedStatement.getGeneratedKeys();
            if(rs.next()){
                challenge.setChallengeId(rs.getLong(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Challenge challenge) throws DBException {
        if(challenge== null){
            return;
        }
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM my_ti.Challenge WHERE ChallengeId = ?");
            preparedStatement.setLong(1,challenge.getChallengeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Challenge getById(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.Challenge WHERE ChallengeId = ?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            Challenge challenge;
            if(rs.next()){
                challenge = new Challenge();
                mapResultSetToObject(challenge,rs);
                return challenge;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Challenge challenge) throws DBException {
        if(challenge == null){
            return;
        }
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE my_ti.challenge SET ChallengeState = ? WHERE ChallengeId = ?");
            preparedStatement.setString(1,challenge.getChallengeState());
            preparedStatement.setLong(2,challenge.getChallengeId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<Challenge> getAllChallenge() throws DBException {
        List<Challenge> list = new ArrayList<>();

        Connection connection = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM  my_ti.challenge");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Challenge challenge = new Challenge();
                mapResultSetToObject(challenge,rs);
                list.add(challenge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return list;
    }

    @Override
    public List<Challenge> getAllChallengeToUserId(Long toUserId) throws DBException {
        List<Challenge> list = new ArrayList<>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.challenge WHERE toUserId = ?");
            preparedStatement.setLong(1,toUserId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Challenge challenge = new Challenge();
                mapResultSetToObject(challenge,rs);
                list.add(challenge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<Challenge> getAllChallengeFromUserId(Long fromUserId) throws DBException {
        List<Challenge> list = new ArrayList<>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.challenge WHERE fromUserId = ?");
            preparedStatement.setLong(1,fromUserId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Challenge challenge = new Challenge();
                mapResultSetToObject(challenge,rs);
                list.add(challenge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list;
    }

    void mapResultSetToObject(Challenge challenge,ResultSet rs) throws SQLException{
        challenge.setChallengeId(rs.getLong(1));
        challenge.setChallengeName(rs.getString(2));
        challenge.setFromUserId(rs.getLong(3));
        challenge.setToUserId(rs.getLong(4));
        challenge.setChallengeState(rs.getString(5));
        challenge.setDescription(rs.getString(6));
        challenge.setEndTime(rs.getTimestamp(7).toLocalDateTime());
    }
}
