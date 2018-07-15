package com.baahubuddy.model;

import java.util.HashMap;
import java.util.Map;

public class Login {

    private String username = null, password = null, pin = null, name = null, pic = null;

    public Login() {
    }

    public Login(String username, String password, String pin, String name, String pic) {
        this.username = username;
        this.password = password;
        this.pin = pin;
        this.name = name;
        this.pic = pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", pin='" + pin + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Map<String, String> getFieldMap() {
        Map<String, String> map = new HashMap();
        map.put("username", "string");
        map.put("password", "string");
        map.put("pin", "string");
        map.put("name", "string");
        map.put("pic", "string");
        return map;
    }

    public String toJson() {
        return "{" +
                "username : " + username +
                ", password : " + password +
                ", pin : '" + pin +
                ", name : '" + name +
                ", pic : '" + pic +
                '}';
    }

    public String toJsonWithModel() {
        return "login : {" +
                "username : " + username +
                ", password : " + password +
                ", pin : '" + pin +
                ", name : '" + name +
                ", pic : '" + pic +
                '}';
    }

}
