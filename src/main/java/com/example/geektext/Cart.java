package com.example.geektext;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    //VARS
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    @OneToOne  (mappedBy = "cart")
    @JsonBackReference
    private User user;

    //GETTERS
    public Long getId() { return cartId; }
    public List<Book> getBooks() { return books; }
    public User getUser() { return user; }

    //SETTERS
    public void setUser(User user) { this.user = user; }
    public void setBooks(Book book){ this.books.add(book);}
    public void removeBook(Book book){ this.books.remove(book);}
}
