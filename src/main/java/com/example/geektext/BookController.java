package com.example.geektext;

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

    //curl test //curl localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2
    //curl test //curl localhost:8080/book/addBook -d bookIsbn=5780 -d name=book4 -d genre=Genre2 -d description=testDesc -d publisher=testPublisher -d yearPublished=2020 -d copiesSold=15 -d price=15
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

    //curl test  //curl localhost:8080/book/findByGenre -d genre=genre1
    @PostMapping (path = "/findByGenre")
    public @ResponseBody String book_findbygenre (@RequestParam String genre)
    {
        List<Book> books = bookRepository.findBybookGenre (genre);

        //testing output
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? "\n" : "");
        return out;
    }

    //curl test  //curl localhost:8080/book/findByIsbn -d isbn=5780
    @PostMapping (path = "/findByIsbn")
    public @ResponseBody String book_findbyisbn (@RequestParam String isbn)
    {
        List<Book> books = bookRepository.findBybookIsbn (isbn);

        //testing output
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? "\n" : "");
        return out;
    }
  
    //curl test //curl localhost:8080/book/allBooks
    @GetMapping(path = "/allBooks")
    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        return bookRepository.findAll();
    }
}
