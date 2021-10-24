package com.example.geektext;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.List;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Book
{
    //VARS
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id // @GeneratedValue (strategy = GenerationType.AUTO)
    private String bookIsbn;

    //VARS
    private String bookName;
    private String bookDescription;
    private String bookPublisher;
    private String bookGenre;       //note: errors out w/query if book_genre, due to query constraints.
    private Integer bookYearPublished;
    private Integer bookCopiesSold;
    private Integer bookPrice;


    @ManyToMany (mappedBy = "books")
    @JsonBackReference
    private List<Cart> carts;

    //TODO: many-to-many relationship between author and book
    //ENTITY relations
    //@ManyToMany (targetEntity = Author.class, mappedBy = "bookId", cascade = CascadeType.ALL)
    //private List<Author> book_author;         //needs to reference author obj/key.
    //entity relationships
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable
    private Set<Author> bookAuthors = new HashSet<>();

    //GETTERS
    public Set<Author> getBookAuthors () { return bookAuthors; }
    public String getBookIsbn()
    {
        return bookIsbn;
    }
    public String getBookName()
    {
        return bookName;
    }
    public String getBookDescription()
    {
        return bookDescription;
    }
    public String getBookPublisher() { return bookPublisher; }
    public String getBook_genre ()
    {
        return bookGenre;
    }
    public Integer getBookYearPublished()
    {
        return bookYearPublished;
    }
    public Integer getBookCopiesSold()
    {
        return bookCopiesSold;
    }
    public Integer getBookPrice()
    {
        return bookPrice;
    }
    public List<Cart> getCarts() { return carts; }

    //SETTERS
    public void setBookAuthors (Author author) { this.bookAuthors.add(author); }
    public void setBookIsbn(String book_isbn)
    {
        this.bookIsbn = book_isbn;
    }
    public void setBookName(String book_name)
    {
        this.bookName = book_name;
    }
    public void setBookDescription(String book_description)
    {
        this.bookDescription = book_description;
    }
    public void setBookPublisher(String book_publisher)
    {
        this.bookPublisher = book_publisher;
    }
    public void setBook_genre (String book_genre)
    {
        this.bookGenre = book_genre;
    }
    public void setBookYearPublished(Integer book_yearPublished)
    {
        this.bookYearPublished = book_yearPublished;
    }
    public void setBookCopiesSold(Integer book_copiesSold)
    {
        this.bookCopiesSold = book_copiesSold;
    }
    public void setBookPrice(Integer book_price)
    {
        this.bookPrice = book_price;
    }

    // use this method to print the book information based on the book's ISBN. 
    @Override
    public String toString()
    {
        return new String ("ISBN: "+this.bookIsbn + ", Name: "+this.bookName + ", Description: "+this.bookDescription + ", Publisher: " + this.bookPublisher + ", Genre: " + this.bookGenre + ", Year Published: " + this.bookYearPublished + ", Copies Sold: " + this.bookCopiesSold + ", Price: " + this.bookPrice);
    }
}
