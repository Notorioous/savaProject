package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserManager {

    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) {

        String query = "INSERT INTO user( `name`, surname, email, password, pic_url )" +
                "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPicUrl());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getUserByEmailAndPassword(String email, String password) {

        String query = "SELECT * FROM `user` WHERE email = '" + email + "'" + "AND password = '" + password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";

        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void sendRequest(int fromId, int toId) {

        String query = "INSERT INTO friend_request (from_id, to_id)" +
                "VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, fromId);
            preparedStatement.setInt(2, toId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Set<User> getFriendRequestUser(int toId) {

        String query = "SELECT from_id FROM friend_request WHERE to_id = " + toId;

        Set<User> requestUsers = new HashSet<User>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                requestUsers.add(getUserById(resultSet.getInt(1)));
            }
            return requestUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addToFriendList(int userId, int friendId) {

        String query = "INSERT INTO user_friend (user_id, friend_id)" +
                "VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeRequest(int fromId, int toId) {

        String query = "DELETE FROM friend_request WHERE from_id =" + fromId + " AND to_id =" + toId;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Set<User> getAllFriends(int userId){
        String query = "SELECT friend_id FROM user_friend WHERE user_id = " + userId;

        Set<User> users = new HashSet<User>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                users.add(getUserById(resultSet.getInt(1)));
            }
            if(getUserId(userId) != null){
                users.addAll(getUserId(userId));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Set<User> getUserId(int id){

        String query = "SELECT user_id FROM user_friend WHERE friend_id = " + id;
        Set<User> users = new HashSet<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                users.add(getUserById(resultSet.getInt(1)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFriend(int userId, int friendId){

        String query = "DELETE FROM user_friend WHERE user_id= " + userId + " AND friend_id = " + friendId;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
