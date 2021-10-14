package com.example.geektext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller //class is a controller (MVC)
@RequestMapping(path = "/book")    //URL's start with "/book" after application path
public class BookController
{
    @Autowired
    private BookRepository bookRepository;

    //curl test //curl -X POST localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2
    //curl test //curl -X POST localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2 -d description=testDesc -d publisher=testPublisher -d yearPublished=2020 -d copiesSold=15 -d price=15
    @PostMapping(path = "/addBook")    //Map *only* POST requests
    public @ResponseBody
    String addBook (@RequestParam String bookIsbn, @RequestParam String name, @RequestParam String genre,
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

    //curl test  //curl -X POST localhost:8080/book/findByGenre -d genre=genre2
    @PostMapping (path = "/findByGenre")
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

    //curl test  //curl -X POST localhost:8080/book/findByIsbn -d isbn=5780
    @PostMapping (path = "/findByIsbn")
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

    //curl test  //curl -X POST localhost:8080/book/findTop10Sold
    @PostMapping (path = "/findTop10Sold")
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

    //curl test //curl -X GET localhost:8080/book/allBooks
    @GetMapping(path = "/allBooks")
    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        //returns a JSON/XML with all books
        return bookRepository.findAll();
    }
}
