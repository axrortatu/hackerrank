package dao;

import model.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Attachment extends BaseDatabaseConnection implements BaseDatabase<model.Attachment>{
    @Override
    public boolean addObject(model.Attachment problem) {
        return false;
    }

    @Override
    public List<model.Attachment> getObjectList() {
        return null;
    }
    public List<model.Attachment> getObjectList(Integer attachmentId) {


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from get_attachment(?)")) {
            preparedStatement.setInt(1, attachmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<model.Attachment> attachments = new ArrayList<>();
            while (resultSet.next()) {
                attachments.add(new model.Attachment(resultSet));
            }
            return attachments;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
