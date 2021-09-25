package com.example.geektext;

import javax.persistence.*;

@Entity
public class Book
{
    //VARS
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer bookId;

    //VARS
    private String bookIsbn;
    private String bookName;
    private String bookDescription;
    private String bookPublisher;
    private String bookGenre;       //note: errors out w/query if book_genre, due to query constraints.
    private Integer bookYearPublished;
    private Integer bookCopiesSold;
    private Integer bookPrice;

    //TODO: many-to-many relationship between author and book
    //ENTITY relations
    //@ManyToMany (targetEntity = Author.class, mappedBy = "bookId", cascade = CascadeType.ALL)
    //private List<Author> book_author;         //needs to reference author obj/key.

    //GETTERS
    public Integer getBookId()
    {
        return bookId;
    }
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
    public String getBookPublisher()
    {
        return bookPublisher;
    }
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

    //SETTERS
    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }
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

    //testing
    @Override
    public String toString()
    {
        return new String ("Book name: "+this.bookName + ", genre: "+this.bookGenre);
    }
}
