package dao;

import common.Pair;
import model.Difficulty;
import model.Problem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        }
        return null;
    }

    public String getProblemInfo(String topicId, Difficulty difficulty, int pageNumber) {
        List<Problem> problemList = getProblemByTopicId(Integer.parseInt(topicId), difficulty, pageNumber);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append("\n");
        }

        return stringBuilder.toString();
    }
    public String getSolvedProblems(Long chat_id){
        List<Problem> problemList = getSolved(chat_id);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append("\n");
        }
        return stringBuilder.toString();
    }
    private List<Problem> getSolved(Long chatId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from get_user_problem_status(?)");
            preparedStatement.setLong(1,chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Problem> problems = new ArrayList<>();
            while(resultSet.next()){
                problems.add(new Problem(resultSet));
            }
            return problems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Problem> getUnsolved(Long chatId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from get_user_problem_status_unsolved(?)");
            preparedStatement.setLong(1,chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Problem> problems = new ArrayList<>();
            while(resultSet.next()){
                problems.add(new Problem(resultSet));
            }
            return problems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getUnsolvedProblems(Long chat_id){
        List<Problem> problemList = getUnsolved(chat_id);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append("\n");
        }
        return stringBuilder.toString();
    }
}
