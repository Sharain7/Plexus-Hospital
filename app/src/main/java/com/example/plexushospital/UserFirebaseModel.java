package com.example.plexushospital;

public class UserFirebaseModel {
    private String userId;
    private String userPhoneNumber;
    private String userFullName;

    public UserFirebaseModel(){

    }


    public UserFirebaseModel(String userId, String userPhoneNumber, String userFullName) {
        this.userId = userId;
        this.userPhoneNumber = userPhoneNumber;
        this.userFullName = userFullName;
    }
}
