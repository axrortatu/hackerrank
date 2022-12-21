package dao;

<<<<<<<<< Temporary merge branch 1
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
=========
import model.Problem;

import java.util.List;

public class AttachmentDatabase extends BaseDatabaseConnection implements BaseDatabase<Problem>{
    @Override
    public boolean addObject(Problem problem) {
>>>>>>>>> Temporary merge branch 2
        return false;
    }

    @Override
<<<<<<<<< Temporary merge branch 1
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

=========
    public List<Problem> getObjectList() {
>>>>>>>>> Temporary merge branch 2
        return null;
    }
}
