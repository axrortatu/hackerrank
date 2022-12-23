package model;

import lombok.*;
import model.base.BaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Question extends BaseModel {
private Integer id;
private String description;
private int problemId;
private int attachmentId;
private String type;
private int order;
private String descriptionUzb;

private String descriptionRus;

    public Question(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.description = resultSet.getString("description");
            this.descriptionUzb = resultSet.getString("description_uzb");
            this.descriptionRus = resultSet.getString("description_rus");
            this.problemId = resultSet.getInt("problem_id");
            this.attachmentId =  resultSet.getInt("attachment_id");
            this.type = resultSet.getString("type");
            this.order = resultSet.getInt("orders");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
