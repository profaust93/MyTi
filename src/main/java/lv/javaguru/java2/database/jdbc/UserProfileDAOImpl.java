package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.profile.database.UserProfileDAO;
import lv.javaguru.java2.profile.domain.UserProfile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Camille on 02.04.2016.
 *
 */
@Component
public class UserProfileDAOImpl extends DAOImpl implements UserProfileDAO {
    @Override
    public void create(UserProfile userProfile) throws DBException {
        if (userProfile == null) {
            return;
        }

        Connection connection = null;

        connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO my_ti.Profiles VALUES (default,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, userProfile.getUserId());
            preparedStatement.setString(2, userProfile.getFirstName());
            preparedStatement.setString(3, userProfile.getLastName());
            preparedStatement.setString(4, userProfile.getEmail());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                userProfile.setProfileId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserProfileDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public UserProfile getById(Long userId) throws DBException {
        Connection connection = null;
        try {
        connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM my_ti.Profiles WHERE userId = ?");
        preparedStatement.setLong(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        UserProfile userProfile = null;
       if (rs.next()){
            userProfile = new UserProfile();
            userProfile.setProfileId(rs.getLong(1));
           userProfile.setUserId(rs.getLong(2));
            userProfile.setFirstName(rs.getString(3));
            userProfile.setLastName(rs.getString(4));
            userProfile.setEmail(rs.getString(5));
        }

        return userProfile;}
        catch  (Throwable e) {
            System.out.println("Exception while execute UserProfileDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long userId) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM my_ti.Profiles WHERE userId = ?");
           preparedStatement.setLong(1,userId);
            preparedStatement.executeUpdate();
        } catch (Throwable e){
            System.out.println("Exception while execute UserProfileDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        }finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(UserProfile userProfile) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE my_ti.Profiles set userId = ?, firstName = ?, lastName = ?, email = ? WHERE UserId = ?");
            preparedStatement.setLong(1,userProfile.getUserId());
            preparedStatement.setString(2,userProfile.getFirstName());
            preparedStatement.setString(3, userProfile.getLastName());
            preparedStatement.setString(4, userProfile.getEmail());
            preparedStatement.setLong(5,userProfile.getUserId());
            preparedStatement.executeUpdate();

        } catch (Throwable e) {
            System.out.println("Exception while execute UserProfileDAOImpl.update()");
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<UserProfile> getAllUserProfile() throws DBException {
        List<UserProfile> allUserProfile = new ArrayList<>();
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM my_ti.Profiles");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                UserProfile userProfile = new UserProfile();
                mapResultSetToObject(userProfile,rs);
                allUserProfile.add(userProfile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allUserProfile;
    }

    void mapResultSetToObject(UserProfile userProfile,ResultSet rs) throws SQLException{
        userProfile.setProfileId(rs.getLong(1));
        userProfile.setUserId(rs.getLong(2));
        userProfile.setFirstName(rs.getString(3));
        userProfile.setLastName(rs.getString(4));
        userProfile.setEmail(rs.getString(5));
    }
}
