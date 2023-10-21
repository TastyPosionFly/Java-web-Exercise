package com.tastypoisonfly.exercise.Data;

import com.tastypoisonfly.exercise.Questions;

//用于存放数据
public class QuestionsData {
    private String name;
    private String sex;
    private String hobbies;
    private String  age;
    private int index;//用于记录不同人的索引

    public QuestionsData(int index,String name,String age,String sex,String hobbies){
        this.index = index;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getHobbies() {
        return hobbies;
    }

    public int getIndex() {
        return index;
    }
}
