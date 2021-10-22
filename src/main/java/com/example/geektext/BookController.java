package com.example.geektext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller //class is a controller (MVC)
@RequestMapping(path = "/book")    //URL's start with "/book" after application path
public class BookController
{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    //POST
    //curl test //curl -X POST localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2 -d authorFullName="first last"
    //curl test //curl -X POST localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2 -d authorFullName="first last" -d description=testDesc -d publisher=testPublisher -d yearPublished=2020 -d copiesSold=15 -d price=15
    @PostMapping(path = "/addBook")    //Map *only* POST requests
    public @ResponseBody
    String addBook (@RequestParam String bookIsbn, @RequestParam String name, @RequestParam String genre,
                    @RequestParam String authorFullName,
                    @RequestParam(required = false) String description, @RequestParam(required = false) String publisher,
                    @RequestParam(required = false) Integer yearPublished, @RequestParam(required = false) Integer copiesSold,
                    @RequestParam(required = false) Integer price)
    {
        // save all of the book's information
        Book book = new Book();

        //required fields
        book.setBookIsbn(bookIsbn);
        book.setBookName(name);
        book.setBook_genre(genre);

        //get & set the author:
        var authors = authorRepository.findByAuthorFullName(authorFullName);
        if (authors.stream().count() == 0 || authors.get(0) == null)
            return "Cannot find author with name: " +authorFullName;
        book.setBookAuthors(authors.get(0));

        //optional fields
        if (description != null) book.setBookDescription(description);
        if (publisher != null) book.setBookPublisher(publisher);
        if (yearPublished != null) book.setBookYearPublished(yearPublished);
        if (copiesSold != null) book.setBookCopiesSold(copiesSold);
        if (price != null) book.setBookPrice(price);

        //save book
        bookRepository.save(book);
        return "Saved book";
    }

    //GET
    //curl test  //curl -X GET localhost:8080/book/findByGenre?genre=genre2
    @RequestMapping (path = "/findByGenre")
    public @ResponseBody String book_findbygenre (@RequestParam String genre)
    {
        List<Book> books = bookRepository.findBybookGenre (genre);

        //JSON out:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //failure message
        return "Failed to find by genre.";
    }

    //GET
    //curl test  //curl -X GET localhost:8080/book/findByIsbn?isbn=5780
    @RequestMapping (path = "/findByIsbn")
    public @ResponseBody String book_findbyisbn (@RequestParam String isbn)
    {
        List<Book> books = bookRepository.findBybookIsbn (isbn);

        //JSON out:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //failure message
        return "Failed to find by ISBN.";
    }

    //GET
    //curl test  //curl -X GET localhost:8080/book/findTop10Sold
    @RequestMapping (path = "/findTop10Sold")
    public @ResponseBody String book_findTop10byCopiesSold ()
    {
        List<Book> books = bookRepository.findTop10ByOrderByBookCopiesSoldDesc();

        //JSON out:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //failure message
        return "Failed to find top 10 books sold.";
    }

    //get a set amt of records from total record set
    //curl localhost:8080/book/getSetOfBooks -d page= 0 -d size=5
    //curl localhost:8080/book/getSetOfBooks?page=0 -d size=10
    @RequestMapping (path = "/getSetOfBooks")
    public @ResponseBody Iterable<Book> getSetOfBooks (@RequestParam Integer page, @RequestParam Integer size)
    {
        //get data sorted by name:
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookName"));

        //output page of data:
        return bookRepository.findAll(pageable).getContent();
    }


    //GET
    //curl test //curl -X GET localhost:8080/book/allBooks
    @GetMapping(path = "/allBooks")
    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        //returns a JSON/XML with all books
        return bookRepository.findAll();
    }
}
