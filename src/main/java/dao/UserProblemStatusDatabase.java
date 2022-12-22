package dao;

import common.Pair;
import model.UserProblemStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProblemStatusDatabase extends BaseDatabaseConnection implements BaseDatabase<UserProblemStatus>{

    @Override
    public boolean addObject(UserProblemStatus userProblemStatus) {
        Connection connection = null;
        Statement statement = null;

        Pair<String, String> pair = new Pair<>();
//        i_user_id bigint, i_problem integer
        pair.put("i_chat_id := ", String.valueOf(userProblemStatus.getChatId()));
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

    public boolean isSolved(long chatId, int problemId){
        List<UserProblemStatus> statuses = getUserSolvedList(chatId);
       return binarySearchUserSolvedList(statuses,problemId);
    }

    public List<UserProblemStatus> getUserSolvedList(long chatId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from get_user_problem_status_chat_id(?)");
            statement.setLong(1,chatId);
            List<UserProblemStatus> userProblemStatuses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
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

    public boolean binarySearchUserSolvedList(List<UserProblemStatus> statusList, int problemID){
        int first =0;
        int last = statusList.size();
        int mid = (first + last)/2;
        while (first<=last){
            if(statusList.get(mid).getProblemId()<problemID){
                first=mid+1;
            }else if(statusList.get(mid).getProblemId()==problemID){
                return true;
            }else {
                last= mid -1;
            }
            mid = (first +last)/2;
        }
        return false;
    }
}