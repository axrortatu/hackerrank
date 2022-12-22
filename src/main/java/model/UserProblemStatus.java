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

public class UserProblemStatus extends BaseModel {

    private Long  chatId;
    private Integer problemId;

    public UserProblemStatus(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.chatId = resultSet.getLong("chat_id");
            this.problemId = resultSet.getInt("problem_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
