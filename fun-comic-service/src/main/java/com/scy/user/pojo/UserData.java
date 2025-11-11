package com.scy.user.pojo;

import java.util.List;

public class UserData {
    private String userid;
    private String newName;
    private int age;
    private String gender;
    private String avatarFile;
    private List<String> carouselFiles;

    public UserData() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public List<String> getCarouselFiles() {
        return carouselFiles;
    }

    public void setCarouselFiles(List<String> carouselFiles) {
        this.carouselFiles = carouselFiles;
    }
}