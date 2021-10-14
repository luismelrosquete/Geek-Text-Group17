package com.example.geektext;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author
{
    //VARS
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id
    private String authorFullName;
    private String authorFirstName;
    private String authorLastName;
    private String authorBiography;
    private String authorPublisher;    //necessary? or, separate publisher entity? as, could be in books/author?

    //entity relationships
    @ManyToMany (mappedBy = "bookAuthors")
    private Set<Book> authorBooks;

    //GETTERS
    //public Integer getAuthorId() { return authorId; }
    public String getAuthorFullName()
    {
        return authorFullName;
    }
    public String getAuthorFirstName()
    {
        return authorFirstName;
    }
    public String getAuthorLastName()
    {
        return authorLastName;
    }
    public String getAuthorBiography()
    {
        return authorBiography;
    }
    public String getAuthorPublisher()
    {
        return authorPublisher;
    }

    //SETTERS
    //public void setAuthorId(Integer authorId) { this.authorId = authorId; }
    public void setAuthorFullName(String author_fullName)
    {
        this.authorFullName = author_fullName;
    }
    public void setAuthorFirstName(String author_firstName)
    {
        this.authorFirstName = author_firstName;
    }
    public void setAuthorLastName(String author_lastName)
    {
        this.authorLastName = author_lastName;
    }
    public void setAuthorBiography(String author_biography)
    {
        this.authorBiography = author_biography;
    }
    public void setAuthorPublisher(String author_publisher) { this.authorPublisher = author_publisher; }
}
