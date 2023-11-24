package com.tastypoisonfly.exercise.Data;

import java.io.Serializable;

public class HobbiesData implements Serializable {
    private String username;
    private String sex;
    private String age;
    private String address;
    private String hobbies;
    public HobbiesData(){

    }

    public HobbiesData(String username,String sex,String age,String address,String hobbies){
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.hobbies = hobbies;
    }

    public String getUsername() {
        return username;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
