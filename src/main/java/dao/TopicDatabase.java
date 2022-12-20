package dao;

import common.Pair;
import model.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicDatabase extends BaseDatabaseConnection implements BaseDatabase<Topic> {

    @Override
    public boolean addObject(Topic topic) {
        Connection connection = null;
        Statement statement = null;

        Pair<String, String> pair = new Pair<>();
        pair.put("i_name := ", topic.getName());

        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet
                    = statement.executeQuery("select add_topic(" + pair.getQueryBuilder() + ")");

            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection,statement);
        }
        return false;
    }

    @Override
    public  List<Topic> getObjectList() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet
                    = statement.executeQuery("select * from get_topic()");

            List<Topic> topicList = new ArrayList<>();
            while (resultSet.next()) {
                topicList.add(new Topic(resultSet));
            }
            return topicList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection,statement);
        }
        return null;
    }


}
