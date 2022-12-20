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
public class User extends BaseModel {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private long attachmentId;


    public User(ResultSet resultSet){
        this.getList(resultSet);
    }

    @Override
    protected void getList(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("id");
            this.name = resultSet.getString("name");
            this.username = resultSet.getString("username");
            this.password = resultSet.getString("password");
            this.email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
