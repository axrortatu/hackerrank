package dao;


import model.Problem;
import model.Attachment;
import model.AttachmentContent;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.List;

public class QuestionDatabase extends BaseDatabaseConnection implements BaseDatabase<Question> {

    @Override
    public boolean addObject(Question question) {
        return false;
    }

    @Override
    public List<Question> getObjectList() {
        return null;
    }

    public boolean addObjectStange(Question question, AttachmentContent attachmentContent, Attachment attachment) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from add_question(?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, question.getProblemId());
            statement.setString(2, question.getType());
            statement.setInt(3, question.getOrder());
            statement.setBytes(4, attachmentContent.getContent());
            statement.setString(5, question.getDescription());
            statement.setString(6, question.getDescriptionUzb());
            statement.setString(7, question.getDescriptionRus());
            statement.setString(8, question.getType());
            statement.setLong(9, attachment.getSize());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            closeConnection(connection, statement);
        }

    }


    public List<Question> getObjectList(Integer questionId) {
        try (Connection connection = getConnection();
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
