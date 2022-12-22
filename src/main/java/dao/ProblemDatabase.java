package dao;

import model.Difficulty;
import model.Problem;
import model.UserProblemStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static bot.BotConstants.NOT_RESOLVED;
import static bot.BotConstants.SOLVED;

public class ProblemDatabase extends BaseDatabaseConnection implements BaseDatabase<Problem> {

    @Override
    public boolean addObject(Problem problem) {
        return false;
    }

    @Override
    public List<Problem> getObjectList() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from get_topic_problems");
            ArrayList<Problem> problemList = new ArrayList<>();
            while (resultSet.next()) {
                problemList.add(new Problem(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Problem> getProblemByTopicId(
            int topicId,
            Difficulty difficulty,
            int pageNumber
    ) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from get_topic_problems(?,?,?)");
            statement.setInt(1, topicId);
            if (difficulty != null) {
                statement.setString(2, difficulty.name());
            }
            if (pageNumber != 0) {
                statement.setInt(3, pageNumber);
            }
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Problem> problemList = new ArrayList<>();
            while (resultSet.next()) {
                problemList.add(new Problem(resultSet));
            }
            return problemList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection,statement);
        }
        return null;
    }

    public String getProblemInfo(String topicId, Difficulty difficulty, int pageNumber, long chatId) {
        List<Problem> problemList = getProblemByTopicId(Integer.parseInt(topicId), difficulty, pageNumber);
        UserProblemStatusDatabase statusDatabase = new UserProblemStatusDatabase();

        StringBuilder stringBuilder = statusDatabase.isSolved(problemList,chatId);


        return stringBuilder.toString();
    }
}