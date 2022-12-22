package dao;

import model.Difficulty;
import model.Problem;

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
        } finally {
            closeConnection(connection, statement);
        }
        return null;
    }

    public String getProblemInfo(String topicId, Difficulty difficulty, int pageNumber, long chatId) {
        List<Problem> problemList = getProblemByTopicId(Integer.parseInt(topicId), difficulty, pageNumber);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String getSolvedProblems(Long chat_id, int topic_id, boolean status) {
        List<Problem> problemList = getSolved(chat_id, topic_id, status);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append(" (").append(problemList.get(i).getDifficulty()).append(")").append("\n");
        }
        return stringBuilder.toString();
    }

    public List<Problem> getSolved(Long chatId, int topic_id, boolean status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from get_topic_problems(?,?,?,?,?,?)");
            preparedStatement.setInt(1, topic_id);
            preparedStatement.setString(2, null);
            preparedStatement.setInt(3, 1);
            preparedStatement.setLong(4, chatId);
            preparedStatement.setInt(5, 0);
            preparedStatement.setBoolean(6, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Problem> problems = new ArrayList<>();
            while (resultSet.next()) {
                problems.add(new Problem(resultSet));
            }
            return problems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
