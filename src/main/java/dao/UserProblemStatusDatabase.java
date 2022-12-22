package dao;

import common.Pair;
import model.UserProblemStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserProblemStatusDatabase extends BaseDatabaseConnection implements BaseDatabase<UserProblemStatus>{

    @Override
    public boolean addObject(UserProblemStatus userProblemStatus) {
        Connection connection = null;
        Statement statement = null;

        Pair<String, String> pair = new Pair<>();
//        i_user_id bigint, i_problem integer
        pair.put("i_telegram_chat_id := ", String.valueOf(userProblemStatus.getTelegram_chat_id()));
        pair.put("i_problem_id := ", String.valueOf(userProblemStatus.getProblemId()));

        try {
            connection =  getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select add_user_problem_status("+pair.getQueryBuilder()+")");
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }closeConnection(connection,statement);

        return false;
    }

    @Override
    public List<UserProblemStatus> getObjectList() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet
                    = statement.executeQuery("select * from get_user_problem_status_chat_id()");

            List<UserProblemStatus> userProblemStatuses = new ArrayList<>();
            while (resultSet.next()) {
                userProblemStatuses.add(new UserProblemStatus(resultSet));
            }
            return userProblemStatuses;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection,statement);
        }
        return null;
    }
    public boolean isSolved(long chatId, int id){
        List<UserProblemStatus> statuses = getObjectList();
        for (UserProblemStatus u:statuses) {
            if(u.getTelegram_chat_id()==chatId && u.getProblemId()==id){
                return true;
            }
        }
        return false;
    }
}
