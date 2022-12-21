package model;

import lombok.*;
import model.base.BaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AttachmentContent extends BaseModel {
    private long id;
    private byte[] content;

    public AttachmentContent(ResultSet resultSet) {
    this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("id");
            this.content = resultSet.getBytes("content");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
