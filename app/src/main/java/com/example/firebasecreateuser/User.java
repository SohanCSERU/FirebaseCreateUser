package com.example.firebasecreateuser;


public class User {

    public String user_name,user_email,password;
    public String phone_number,blood_group;
    public String last_donation;

    public User(){}

    public User(String user_name, String user_email, String phone_number, String blood_group, String last_donation,String password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.phone_number = phone_number;
        this.blood_group = blood_group;
        this.last_donation = last_donation;
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getLast_donation() {
        return last_donation;
    }

    public void setLast_donation(String last_donation) {
        this.last_donation = last_donation;
    }
}
