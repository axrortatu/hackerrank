package dao;


import model.AttachmentContent;
import model.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;




public class AttachmentContantDatabase extends BaseDatabaseConnection implements BaseDatabase<AttachmentContantDatabase>{


    @Override
    public boolean addObject(AttachmentContantDatabase attachmentContantDatabase) {
        return false;
    }

    public List<AttachmentContantDatabase> getObjectList() {
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


