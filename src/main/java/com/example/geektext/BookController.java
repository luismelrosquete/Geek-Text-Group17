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

    //curl test //curl localhost:8080/book/addBook -d name=book4 -d genre=genre2 -d price=15
    @PostMapping(path = "/addBook")    //Map *only* POST requests
    public @ResponseBody
    String addBook (@RequestParam String name, @RequestParam String genre,
                    @RequestParam Integer price)
    {
        Book book = new Book();
        book.setBookName(name);
        book.setBook_genre(genre);
        book.setBookPrice(price);
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
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? " | " : "");
        return out;
    }

    //curl test //curl localhost:8080/book/allBooks
    @GetMapping(path = "/allBooks")
    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        return bookRepository.findAll();
    }
}
