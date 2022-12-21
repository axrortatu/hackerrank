package dao;

import model.Problem;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDatabase extends BaseDatabaseConnection implements BaseDatabase<Question>{
    @Override
    public boolean addObject(Question question) {
        return false;
    }

    @Override
    public List<Question> getObjectList() {
        return null;
    }
    public List<Question> getObjectList(Integer questionId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from get_question(?)")) {

            preparedStatement.setInt(1, questionId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Question> questions = new ArrayList<>();

            while (resultSet.next()) {
                questions.add(new Question(resultSet));
            }
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
