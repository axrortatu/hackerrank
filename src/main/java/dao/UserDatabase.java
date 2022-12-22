package dao;

import model.User;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class UserDatabase extends BaseDatabaseConnection implements BaseDatabase<User> {

    @Override
    public boolean addObject(User user) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from add_user(?,?,?)");) {

            preparedStatement.setLong(1, user.getChatId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
           resultSet.next();
           return resultSet.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
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

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where chat_id=?")){
            preparedStatement.setLong(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }
}
