package com.example.geektext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller //class is a controller (MVC)
@RequestMapping(path = "/author")
public class AuthorController
{
    @Autowired
    private AuthorRepository authorRepository;

    //POST
    //curl localhost:8080/author/addAuthor -d first=first -d last=last
    //curl localhost:8080/author/addAuthor -d first=first -d last=last -d biography="this is the authors bio" -d publisher=testPublisher
    @RequestMapping(path="/addAuthor")
    public @ResponseBody String addAuthor (@RequestParam String first, @RequestParam String last,
                                           @RequestParam(required = false) String biography,
                                           @RequestParam(required = false) String publisher)
    {
        Author author = new Author();

        //add vars
        author.setAuthorFirstName(first);
        author.setAuthorLastName(last);
        author.setAuthorFullName(first + " " + last);

        //optional vars:
        if (biography != null) author.setAuthorBiography(biography);
        if (publisher != null) author.setAuthorPublisher(publisher);

        //
        authorRepository.save(author);
        return "Saved author";
    }

    //GET
    //curl localhost:8080/author/findAuthorByName -d fullName="first last"
    @RequestMapping (path = "/findAuthorByName")
    public @ResponseBody String findAuthorByName (@RequestParam String fullName)
    {
        var authors = authorRepository.findByAuthorFullName(fullName);

        //JSON out:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(authors);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "Failed to find author with fullName: "+fullName;
    }

    //GET
    //curl test //curl -X GET localhost:8080/author/allAuthors
    @GetMapping(path = "/allAuthors")
    public @ResponseBody Iterable<Author> getAllAuthors ()
    {
        //returns a JSON/XML with all books
        return authorRepository.findAll();
    }
}
