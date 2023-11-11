package com.tastypoisonfly.exercise.Data;


import java.text.SimpleDateFormat;
import java.util.*;

public class UserMessagesData {
    private String userName;
    private List<String> privateMessages;
    private long lastAccessedTime;//最后刷新的时间

    public UserMessagesData(String userName){
        this.userName = userName;
        privateMessages = new ArrayList<>();
    }

    public void receiverMessage(String sender,String message){
        Date currentTime = new Date();

        // 创建一个SimpleDateFormat对象，定义时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间为字符串
        String formattedTime = sdf.format(currentTime);

        String privateMessage = formattedTime+sender+"对你说"+message;
        privateMessages.add(privateMessage);
    }

    public List<String> getPrivateMessages() {
        return privateMessages;
    }

    public void setLastAccessedTime(long currentTime) {
        this.lastAccessedTime = currentTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public String getUserName() {
        return userName;
    }
}
