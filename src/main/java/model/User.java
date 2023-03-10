package model;

import lombok.*;
import model.base.BaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseModel {
    private long id;
    private long chatId;
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
            this.chatId = resultSet.getLong("chat_id");
            this.name = resultSet.getString("name");
            this.username = resultSet.getString("username");
            this.password = resultSet.getString("password");
            this.email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String buildAddUserSQL(User user){

        return user.chatId+",'"+
                user.getName()+"',"+
                ((Objects.isNull(user.username))?"null":"'"+user.getUsername())+"',"+
                "null,null";
    }
}
