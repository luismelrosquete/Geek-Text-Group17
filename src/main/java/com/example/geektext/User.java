package com.example.geektext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity     //tells hibernate to make a table out of this class
public class User
{
    //this is our key for the entity, i believe, for "main access point" via SQL)
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;

    //GETTERS
    public String getEmail()
    {
        return email;
    }
    public Integer getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }

    //SETTERS
    public void setId(Integer id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
}
