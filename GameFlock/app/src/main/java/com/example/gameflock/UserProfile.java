package com.example.gameflock;

public class UserProfile {
    private String userName;
    private String userEmail;
    private String userPhone;
    private String profileImageUrl;

    public UserProfile() {
        // Default constructor required for Firebase
    }

    public UserProfile(String userName, String userEmail, String userPhone, String profileImageUrl) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}

