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

    //curl test //curl localhost:8080/book/addBook -d bookIsbn=5780 -d name=Book2 -d description=ExampleOne -d publisher=BooksX -d genre=Genre2 -d yearPublished=2017 -d copiesSold=2500 -d price=9
    @PostMapping(path = "/addBook")    //Map *only* POST requests
    public @ResponseBody
    String addBook (@RequestParam String bookIsbn, @RequestParam String name, @RequestParam String description, @RequestParam String publisher, @RequestParam String genre,
                    @RequestParam Integer yearPublished, @RequestParam Integer copiesSold, @RequestParam Integer price)
    { // save all of the book's information
        Book book = new Book(); 
        book.setBookIsbn(bookIsbn);
        book.setBookName(name);
        book.setBookDescription(description);
        book.setBookPublisher(publisher);
        book.setBook_genre(genre);
        book.setBookYearPublished(yearPublished);
        book.setBookCopiesSold(copiesSold);
        book.setBookPrice(price);
        bookRepository.save(book);
        return "Saved book";
    }

    //curl test  //curl localhost:8080/book/findByGenre -d genre=genre1
    @PostMapping (path = "/findByGenre")
    public @ResponseBody String book_findbygenre (@RequestParam String genre)
    {
        List<Book> books = bookRepository.findBybookGenre (genre);

        //none found
        if (books.stream().count() == 0)
            return "No books found with genre: \""+genre+"\"";

        //testing output
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? " \n" : "");
        return out;
    }

    //curl test  //curl localhost:8080/book/findbyisbn -d isbn=4987
    @PostMapping (path = "/findbyisbn")
    public @ResponseBody String book_findbyisbn (@RequestParam String isbn)
    {
        List<Book> books = bookRepository.findBybookIsbn (isbn);

        //none found
        if (books.stream().count() == 0)
            return "No books found with ISBN: \""+isbn+"\"";

        //testing output
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? " \n" : "");
        return out;
    }
  
    //curl test //curl localhost:8080/book/allBooks
    @GetMapping(path = "/allBooks")

    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        return bookRepository.findAll();
    }
}
