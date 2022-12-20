package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.base.BaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attachment extends BaseModel {
    private long id;
    private long size;
    private long attachmentId;
    private String contentType;
    private String fileName;

    public Attachment(ResultSet resultSet) {
    this.getList(resultSet);
    }


    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("id");
            this.size = resultSet.getLong("size");
            this.attachmentId = resultSet.getLong("attachment_id");
            this.contentType = resultSet.getString("content_ty");
            this.fileName = resultSet.getString("file_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
