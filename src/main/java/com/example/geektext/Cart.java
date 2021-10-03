package com.example.geektext;

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
    @OneToMany(targetEntity = Book.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "cb_fk", referencedColumnName = "cartId")
    private List<Book> books;

    //GETTERS
    public Long getId() { return cartId; }
    public Integer getQuantity() { return quantity; }


    //SETTERS
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
