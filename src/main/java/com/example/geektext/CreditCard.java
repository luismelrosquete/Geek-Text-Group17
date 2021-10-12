package com.example.geektext;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity     //tells hibernate to make a table out of this class
public class CreditCard {
    //VARS
    @Id
    private String cardNumber;
    private int cardSecurityPin;
    private int cardExpiryMonth;
    private int cardExpiryYear;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userName", nullable = false)
    @JsonBackReference
    private User user;

    //GETTERS
    public String getCardNumber()
    {
        return cardNumber;
    }
    public int getCardSecurityPin()
    {
        return cardSecurityPin;
    }
    public int getCardExpiryMonth()
    {
        return cardExpiryMonth;
    }
    public int getCardExpiryYear()
    {
        return cardExpiryYear;
    }
    public User getUser() { return user;}

    //SETTERS
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
    public void setUser (User user) { this.user = user; }
    public void setCardSecurityPin(int cardSecurityPin)
    {
        this.cardSecurityPin = cardSecurityPin;
    }
    public void setCardExpiryMonth(int cardExpiryMonth)
    {
        this.cardExpiryMonth = cardExpiryMonth;
    }
    public void setCardExpiryYear(int cardExpiryYear)
    {
        this.cardExpiryYear = cardExpiryYear;
    }

    @Override
    public String toString(){
        return "[" + cardNumber + " " + cardSecurityPin + " " + cardExpiryMonth + " " +
                cardExpiryYear + "]";
    }
}
