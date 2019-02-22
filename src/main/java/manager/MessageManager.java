package manager;

import db.DBConnectionProvider;
import model.Message;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private Connection connection;

    public MessageManager(){
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addMessage(Message message){

        String query = "INSERT INTO `messages` (`from_id`, `to_id`, `text`, `sent_time`)" +
                "VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,message.getFromId().getId());
            preparedStatement.setInt(2,message.getToId().getId());
            preparedStatement.setString(3,message.getText());
            preparedStatement.setString(4,DateUtil.convertDateToString(message.getDate()));
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                message.setId(generatedKeys.getInt(1));
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public List<Message> getReceivedMessages(int toId){
//        String query = "SELECT from_id FROM messages WHERE to_id = " + toId;
//
//        List<Message> messages = new ArrayList<Message>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                Message message = getMessageById(resultSet.getInt(1));
//                messages.add(message);
//            }
//            return messages;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public Message getMessageById(int id){
//        String query = "SELECT * FROM messages WHERE from_id = " + id;
//
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            if (resultSet.next()){
//                Message message = new Message();
//                message.setId(resultSet.getInt(1));
//                message.setFromId(new UserManager().getUserById(resultSet.getInt(2)));
//                message.setToId(new UserManager().getUserById(resultSet.getInt(3)));
//                message.setText(resultSet.getString(4));
//                message.setDate(DateUtil.convertStringToDate(resultSet.getString(5)));
//                return message;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    public List<Message> getReceivedMessage(int fromId, int toId){
        String query = "SELECT * FROM messages WHERE from_id = " + fromId + " AND to_id = " + toId;

        List<Message> messages = new ArrayList<Message>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFromId(new UserManager().getUserById(resultSet.getInt(2)));
                message.setToId(new UserManager().getUserById(resultSet.getInt(3)));
                message.setText(resultSet.getString(4));
                message.setDate(DateUtil.convertStringToDate(resultSet.getString(5)));
                messages.add(message);
            }
            if(getOtherMessages(fromId,toId) != null){
                messages.addAll(getOtherMessages(fromId,toId));
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getOtherMessages(int fromId, int toId){
        String query = "SELECT * FROM messages WHERE from_id = " + toId + " AND to_id = " + fromId;

        List<Message> messages = new ArrayList<Message>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFromId(new UserManager().getUserById(resultSet.getInt(2)));
                message.setToId(new UserManager().getUserById(resultSet.getInt(3)));
                message.setText(resultSet.getString(4));
                message.setDate(DateUtil.convertStringToDate(resultSet.getString(5)));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
