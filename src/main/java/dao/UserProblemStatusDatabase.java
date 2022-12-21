package dao;

import common.Pair;
import model.UserProblemStatus;

import java.sql.*;
import java.util.List;

public class UserProblemStatusDatabase extends BaseDatabaseConnection implements
BaseDatabase<UserProblemStatus>{
    @Override
    public boolean addObject(UserProblemStatus userProblemStatus) {
        try(Connection connection= getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select add_user_problem_status(?,?)"
            )
        ){
            preparedStatement.setLong(1, userProblemStatus.getUser_id());
            preparedStatement.setInt(2,userProblemStatus.getProblem_id());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserProblemStatus> getObjectList() {
        return null;
    }
}
