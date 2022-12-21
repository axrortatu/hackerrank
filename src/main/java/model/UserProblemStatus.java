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

public class UserProblemStatus extends BaseModel {
    private Long user_id;
    private int problem_id;

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.user_id = resultSet.getLong("user_id");
            this.problem_id = resultSet.getInt("problem_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
