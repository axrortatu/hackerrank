package dao;

import model.UserPreparation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPreparationDataBase extends BaseDatabaseConnection implements BaseDatabase<UserPreparation>{
    @Override
    public boolean addObject(UserPreparation userPreparationDataBase) {
        return false;
    }

    @Override
    public List<UserPreparation> getObjectList() {
        return null;
    }

    public List<UserPreparation> getUserPreparationByTopicId(long userId, int topicId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from get_user_preparation(?,?)");
            statement.setLong(1, userId);
            statement.setInt(2, topicId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<UserPreparation> userPreparationList = new ArrayList<>();
            while (resultSet.next()) {
                userPreparationList.add(new UserPreparation(resultSet));
            }
            return userPreparationList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserPreparation(long userChatId, int topicId) {
        List<UserPreparation> objectList = getUserPreparationByTopicId(userChatId, topicId);
        StringBuilder stringBuilder = new StringBuilder();
        for (UserPreparation userPreparation : objectList) {
            stringBuilder.append("Topic name \uD83D\uDCC4 : " + userPreparation.getTopicName() + "\n")
                    .append("Stars ⭐️: " + userPreparation.getGainedStars() + "\n")
                    .append("Completed percent ✅ : " +userPreparation.getCompletedPercent() + "% \n")
                    .append("Remaining points \uD83D\uDCCC : " + userPreparation.getRestPoint());
        }
        return stringBuilder.toString();
    }
}
