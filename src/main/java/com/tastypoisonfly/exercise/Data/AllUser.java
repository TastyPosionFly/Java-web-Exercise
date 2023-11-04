package com.tastypoisonfly.exercise.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllUser {
    private List<String> ChatRoamMessage;

    public AllUser(){
        ChatRoamMessage = new ArrayList<>();
    }

    public void sendAllUser(String userName,String message){
        Date currentTime = new Date();

        // 创建一个SimpleDateFormat对象，定义时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间为字符串
        String formattedTime = sdf.format(currentTime);

        String temp = formattedTime + userName+"对全体说"+message;
        ChatRoamMessage.add(temp);
    }

    public List<String> getChatRoamMessage() {
        return ChatRoamMessage;
    }
}
