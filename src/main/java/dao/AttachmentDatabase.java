package dao;

import model.Problem;

import java.util.List;
import model.Attachment;
import model.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDatabase extends BaseDatabaseConnection implements BaseDatabase<Attachment> {
    @Override
    public boolean addObject(model.Attachment problem) {
        return false;
    }
    public List<model.Attachment> getObjectList() {
        return null;
    }

    public Attachment getObjectById(Integer attachmentId) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from get_attachment(?)")) {
            preparedStatement.setInt(1, attachmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Attachment attachment = null;
            while (resultSet.next()) {
                attachment = new Attachment(resultSet);
            }
            return attachment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
