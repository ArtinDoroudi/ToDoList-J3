package org.example.projectj3.pojo;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private boolean isPremium;

    // Constructors
    public User(String userName, String email, String password, boolean isPremium) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isPremium = isPremium;
    }

    public User() {}

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isPremium=" + isPremium +
                '}';
    }
}
