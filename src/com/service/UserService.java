package com.service;

import com.bean.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap = new HashMap<>();

    private User currentUser = null;

    public boolean registerUser(String userName, String password, String fullName, String contact){
        if (userMap.containsKey(userName)){
            System.out.println("Username already Exist! Plz choose another...!");
            return false;
        }
        User user = new User(userName, password, fullName, contact);
        userMap.put(userName, user);
        System.out.println("Registration Successful!");
        return true;
    }

    public boolean loginUser(String userName, String password){
        if (!userMap.containsKey(userName)){
            System.out.println("No user found with this Username...");
            return false;
        }

        User user = userMap.get(userName);
        if (!user.getPassword().equals(password)){
            System.out.println("Incorrect password!!!");
            return false;
        }

        currentUser = user;
        System.out.println("Welcome "+currentUser.getFullName()+" !");
        return true;
    }

    public void logoutUser(){
        if (currentUser != null){
            System.out.println("Logged out..."+currentUser.getFullName());
        }
        currentUser = null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public boolean isLoggedIn(){
        return currentUser != null;
    }

    // For testing, view all registered users
    public void showAllUsers() {
        if (userMap.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User u : userMap.values()) {
            System.out.println(u);
        }
    }
}

