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
