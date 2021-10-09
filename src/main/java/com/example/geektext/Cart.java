package com.example.geektext;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    //VARS
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long cartId;

    //VARS
    /*
    variables like price, books, etc. are going to be used from other classes, so not
    much need for anything other variables to be added unless I'm forgetting something?
    */
    private Integer quantity;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;

    @OneToOne  (mappedBy = "cart")
    private User user;

    //GETTERS
    public Long getId() { return cartId; }
    public Integer getQuantity() { return quantity; }
    public List<Book> getBooks() { return books; }
    public User getUser() { return user; }

    //SETTERS
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setUser(User user) { this.user = user; }
}
