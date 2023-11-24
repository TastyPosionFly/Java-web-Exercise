package Service;

import com.tastypoisonfly.exercise.Data.MessageData;
import db.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Messages {
    private final Connection connection = MyConnection.getConnection();

    //插入新的留言
    public boolean newMessage(int userID,String title,String text){
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
        String sql = "INSERT INTO dbo.Messages(Title,AuthorID,PublishTime,Text) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,title);
            preparedStatement.setInt(2,userID);
            preparedStatement.setTimestamp(3,currentTimestamp);
            preparedStatement.setString(4,text);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllTitle() {
        List<String> titles = new ArrayList<>();
        String sql = "SELECT Title FROM dbo.Messages";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("Title");
                titles.add(username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return titles;
    }

    //获取当前留言的作者
    private String getUsername(String title){
        String sql = "SELECT Users.Username FROM Messages " +
                "JOIN Users ON Messages.AuthorID = Users.UserID "+
                "WHERE Messages.Title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                // 检查是否存在当前行
                if (resultSet.next()) {
                    // 从当前行获取密码
                    return resultSet.getString("Username");
                } else {
                    // 用户不存在，或其他处理逻辑
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MessageData getDetailMessage(String title){

        String sql = "SELECT PublishTime, Text FROM Messages WHERE Title = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,title);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    String username = getUsername(title);
                    String text = resultSet.getString("Text");
                    Timestamp publishTime = resultSet.getTimestamp("PublishTime");
                    return new MessageData(title,username,text,publishTime);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
