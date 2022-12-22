package model;

import common.Pair;
import lombok.*;
import model.base.BaseModel;
import model.dto.receive.BaseReceive;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Topic extends BaseModel {
    private int id;
    private String name;

    public Topic(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
