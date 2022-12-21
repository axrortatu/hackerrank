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
public class UserPreparation extends BaseModel {
    private String topicName;
    private int completedPercent;
    private int gainedStars;
    private int restPoint;

    public UserPreparation(ResultSet resultSet) {
        this.getList(resultSet);
    }
    @Override
    protected void getList(ResultSet resultSet) {
        try{
            this.topicName = resultSet.getString("topic_name");
            this.completedPercent = resultSet.getInt("completed_percent");
            this.gainedStars = resultSet.getInt("gain_stars");
            this.restPoint = resultSet.getInt("rest_point");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
