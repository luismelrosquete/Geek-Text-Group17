package com.example.geektext;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity     //tells hibernate to make a table out of this class
public class User
{
    //VARS
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id
    private String userName;    //changed user key to string in userrepository. id no longer necessary
    private String userPw;
    private String userEmail;
    private String userFullName;
    private String userAddress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CreditCard> creditCards;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "cartId", referencedColumnName = "cartId")
    private Cart cart;

    //GETTERS
    public String getUserName() { return userName; }
    public String getUserPw() { return userPw; }
    public String getUserEmail() { return userEmail; }
    public String getUserFullName() { return userFullName; }
    public String getUserAddress() { return userAddress; }
    public List<CreditCard> getCreditCards() { return creditCards; }
    public Cart getCart() { return cart; }

    //SETTERS
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserPw(String user_pw) { this.userPw = user_pw; }
    public void setUserEmail(String user_email) { this.userEmail = user_email; }
    public void setUserFullName(String user_fullName) { this.userFullName = user_fullName; }
    public void setUserAddress(String user_address) { this.userAddress = user_address; }
    public void setCart(Cart cart) { this.cart = cart; }
}
