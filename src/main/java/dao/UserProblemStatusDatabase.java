package dao;

import common.Pair;
import model.Problem;
import model.UserProblemStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static bot.BotConstants.NOT_RESOLVED;
import static bot.BotConstants.SOLVED;

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
        return null;
    }

    public StringBuilder isSolved(List<Problem> problemList,long chatId){
        List<UserProblemStatus> statuses = getUserSolvedList(chatId);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < problemList.size(); i++) {
            if(binarySearchUserSolvedList(statuses,problemList.get(i).getId())){
                stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append(SOLVED).append("\n");
            }else{
                stringBuilder.append(i + 1).append(". ").append(problemList.get(i).getName()).append(NOT_RESOLVED).append("\n");
            }
        }
       return stringBuilder;

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
        if(statusList.get(last-1).getProblemId()<problemID ||
                statusList.get(0).getProblemId()>problemID
        ){
            return false;
        }
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