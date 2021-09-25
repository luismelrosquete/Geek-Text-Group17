package com.example.geektext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity     //tells hibernate to make a table out of this class
public class User
{
    //VARS
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;    //gives an error if using as key/ID
    private String userPw;
    private String userEmail;
    private String userFullName;
    private String userAddress;

    //GETTERS
    public Integer getUserId() { return userId; }
    public String getUserName()
    {
        return userName;
    }
    public String getUserPw()
    {
        return userPw;
    }
    public String getUserEmail()
    {
        return userEmail;
    }
    public String getUserFullName()
    {
        return userFullName;
    }
    public String getUserAddress()
    {
        return userAddress;
    }

    //SETTERS
    public void setUserId (Integer userId) { this.userId = userId; }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setUserPw(String user_pw)
    {
        this.userPw = user_pw;
    }
    public void setUserEmail(String user_email)
    {
        this.userEmail = user_email;
    }
    public void setUserFullName(String user_fullName)
    {
        this.userFullName = user_fullName;
    }
    public void setUserAddress(String user_address)
    {
        this.userAddress = user_address;
    }
}
