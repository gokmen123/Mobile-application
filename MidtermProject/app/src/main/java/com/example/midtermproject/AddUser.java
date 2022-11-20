package com.example.midtermproject;

public interface AddUser {
    boolean passwordNotEqual();
    void addUser(String userName,String Password,String email);
    boolean userNameTaken();

}
