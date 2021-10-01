package com.example.geektext;

import javax.persistence.*;

@Entity
public class Cart {
    //VARS
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long CartId;

    //VARS
    /*
    variables like price, books, etc. are going to be used from other classes, so not
    much need for anything other variables to be added unless I'm forgetting something?
    */
    private Integer quantity;

    //GETTERS
    public Long getId() { return CartId; }
    public Integer getQuantity() { return quantity; }


    //SETTERS
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
