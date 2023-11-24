package Service;

import db.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
    public boolean newUser(String username,String password){
        Connection connection= MyConnection.getConnection();
        String sql="INSERT INTO dbo.Users(Username,Password) VALUES (?,?)";
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.execute();
            return  true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        String sql = "SELECT Username FROM dbo.Users";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("Username");
                usernames.add(username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usernames;
    }

    public String getUserPassword(String username){
        Connection connection = MyConnection.getConnection();
        String sql = "SELECT Password FROM dbo.Users WHERE Username=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                // 检查是否存在当前行
                if (resultSet.next()) {
                    // 从当前行获取密码
                    return resultSet.getString("Password");
                } else {
                    // 用户不存在，或其他处理逻辑
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserID(String username){
        Connection connection = MyConnection.getConnection();
        String sql = "SELECT UserID FROM dbo.Users WHERE Username=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                // 检查是否存在当前行
                if (resultSet.next()) {
                    // 从当前行获取密码
                    return resultSet.getInt("UserID");
                } else {
                    // 用户不存在，或其他处理逻辑
                    return -1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername(int userID){
        Connection connection = MyConnection.getConnection();
        String sql = "SELECT Username FROM dbo.Users WHERE UserID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
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
}
