package com.baahubuddy.model;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private int id = 0;
    private String name="", phone="",photo="",address="";

    public Customer() {
    }

    public Customer(int id, String name, String phone, String photo, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }



    public Map<String, String> getFieldMap() {
        Map<String, String> map = new HashMap();
        map.put("id", "int");
        map.put("name", "string");
        map.put("phone", "string");
        map.put("photo", "string");
        map.put("address", "string");
        return map;
    }

    public String toJson() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", phone:'" + phone + '\'' +
                ", photo:'" + photo + '\'' +
                ", address:'" + address + '\'' +
                '}';
    }

    public String toJsonWithModel() {
        return "login : {" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", phone:'" + phone + '\'' +
                ", photo:'" + photo + '\'' +
                ", address:'" + address + '\'' +
                '}';
    }
}
