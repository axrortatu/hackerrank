package model;

import lombok.*;
import model.base.BaseModel;

import java.sql.ResultSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@ToString
public class Problem extends BaseModel {
    private int id;
    private String name;
    private Difficulty difficulty;
    private int subDomainId;
    private int topicId;

    public Problem(ResultSet resultSet) {
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id= resultSet.getInt("id");
            this.name=resultSet.getString("name");
            this.difficulty= Difficulty.valueOf(resultSet.getString("difficulty"));
            this.subDomainId=resultSet.getInt("subdomain_id");
            this.topicId= resultSet.getInt("topic_id");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
