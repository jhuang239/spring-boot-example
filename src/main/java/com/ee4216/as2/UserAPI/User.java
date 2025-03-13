package com.ee4216.as2.UserAPI;

public class User {
    private String userid;
    private String password;
    private String name;

    public User(String userid, String password, String name) {
        this.userid = userid;
        this.password = password;
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
