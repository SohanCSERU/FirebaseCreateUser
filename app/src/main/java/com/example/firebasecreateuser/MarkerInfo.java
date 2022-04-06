package com.example.firebasecreateuser;

public class MarkerInfo {

    public String user_name;
    public String phone_number,blood_group;
    public String last_donation;

    public MarkerInfo(){

    }
    public MarkerInfo(String user_name, String phone_number, String blood_group, String last_donation) {
        this.user_name = user_name;
        this.phone_number = phone_number;
        this.blood_group = blood_group;
        this.last_donation = last_donation;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
