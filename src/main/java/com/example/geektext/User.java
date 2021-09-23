package com.example.geektext;

import org.hibernate.annotations.GenericGenerator;

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
    private String user_pw;
    private String user_email;
    private String user_fullName;
    private String user_address;

    //GETTERS
    public Integer getUserId() { return userId; }
    public String getUserName()
    {
        return userName;
    }
    public String getUser_pw()
    {
        return user_pw;
    }
    public String getUser_email()
    {
        return user_email;
    }
    public String getUser_fullName()
    {
        return user_fullName;
    }
    public String getUser_address()
    {
        return user_address;
    }

    //SETTERS
    public void setUserId (Integer userId) { this.userId = userId; }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setUser_pw(String user_pw)
    {
        this.user_pw = user_pw;
    }
    public void setUser_email(String user_email)
    {
        this.user_email = user_email;
    }
    public void setUser_fullName(String user_fullName)
    {
        this.user_fullName = user_fullName;
    }
    public void setUser_address(String user_address)
    {
        this.user_address = user_address;
    }
}
