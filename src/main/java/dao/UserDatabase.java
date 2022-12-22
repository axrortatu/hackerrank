package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class UserDatabase extends BaseDatabaseConnection implements BaseDatabase<User> {

    @Override
    public boolean addObject(User user) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select add_user(" + user.buildAddUserSQL(user) + ")");
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection) && Objects.nonNull(statement)) {
                closeConnection(connection, statement);
            }
        }
        return false;
    }

    @Override
    public List<User> getObjectList() {
//        Connection connection = null;
//        Statement statement = null;
//        try {
//            connection = getConnection();
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from get_users()");
//            List<User> userList = new ArrayList<>();
//            while (resultSet.next()) {
//                userList.add(new User(resultSet));
//            }
//            return userList;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (Objects.nonNull(connection) && Objects.nonNull(statement)) {
//                closeConnection(connection, statement);
//            }
//        }
        return null;
    }

    public User getUserByID(Long chatId) {

        Connection connection = null;
        Statement statement = null;
        try{
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where chat_id = " + chatId);
            resultSet.next();
            return new User(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (Objects.nonNull(connection) && Objects.nonNull(statement)) {
                closeConnection(connection, statement);
            }
        }

        return null;

    }
}
