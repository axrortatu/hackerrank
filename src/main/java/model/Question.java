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
public class Question extends BaseModel {
Integer id;
String description;
int problemId;
int attachmentId;
String type;
int order;

    public Question(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.description = resultSet.getString("description");
            this.problemId = resultSet.getInt("problem_id");
            this.attachmentId =  resultSet.getInt("attachment_id");
            this.type = resultSet.getString("type");
            this.order = resultSet.getInt("order");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
