package dao;

import model.Attachment;
import model.AttachmentContent;
import model.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentContant extends BaseDatabaseConnection implements BaseDatabase<model.AttachmentContent>{
    @Override
    public boolean addObject(AttachmentContent problem) {
        return false;
    }

    @Override
    public List<AttachmentContent> getObjectList() {
        return null;
    }
    public List<AttachmentContent> getObjectList(Integer attachmentContentId) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from get_attachment_content(?)")) {

            preparedStatement.setInt(1, attachmentContentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<AttachmentContent> attachmentContents = new ArrayList<>();
            while (resultSet.next()) {
                attachmentContents.add(new AttachmentContent(resultSet));
            }
            return attachmentContents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}













