package dao;

import model.Problem;

import java.util.List;
import model.Attachment;
import model.AttachmentContent;
import model.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentContantDatabase extends BaseDatabaseConnection implements BaseDatabase<model.AttachmentContent> {
    @Override
    public boolean addObject(AttachmentContent problem) {
        return false;
    }

    @Override
    public List<AttachmentContent> getObjectList() {
        return null;
    }

    public AttachmentContent getObjectById(Integer attachmentContentId) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from get_attachment_content(?)")) {

            preparedStatement.setInt(1, attachmentContentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            AttachmentContent attachmentContent = null;
            while (resultSet.next()) {
                attachmentContent = new AttachmentContent(resultSet);
            }
            return attachmentContent;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

