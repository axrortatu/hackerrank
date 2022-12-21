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

    private Long telegram_chat_id;
    private Integer problemId;

    public UserProblemStatus(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.telegram_chat_id = resultSet.getLong("telegram_chat_id");
            this.problemId = resultSet.getInt("problem_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
