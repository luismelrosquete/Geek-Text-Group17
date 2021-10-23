package com.example.geektext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/cart")    //URL's start with "/user" after application path
public class CartController {

    @Autowired  //get the bean called TestRepository    //auto gen'd by spring, used to handle data
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    //Feature: Must be able to update the shopping cart with a book.
    //test curl: curl -X POST localhost:8080/cart/updateCart -d userName=testUserName -d bookIsbn=5780
    @RequestMapping (path = "/updateCart")    //Map *only* POST requests
    public @ResponseBody String updateCart (@RequestParam String userName, @RequestParam String bookIsbn,
                                            @RequestParam(required = false) String name)
    {
        List<User> users = userRepository.findByuserName(userName);
        List<Book> books = bookRepository.findBybookIsbn(bookIsbn);
        if(users.isEmpty()) return "Username does not exist.";
        else if(books.isEmpty()) return "Book does not exist.";
        else{
            Cart cart = users.get(0).getCart();
            cart.setBooks(books.get(0));
            return "Cart has been updated";
        }
    }

    //Feature: Must be able to retrieve the list of book(s) in the shopping cart.
    //test curl: curl -X GET localhost:8080/cart/retrieveCart?userName=testUserName
    @RequestMapping (path = "/retrieveCart")    //Map *only* POST requests
    public @ResponseBody String retrieveCart (@RequestParam String userName)
    {
        List<User> users = userRepository.findByuserName(userName);
        if(users.isEmpty()) return "Username does not exist.";
        Cart cart = users.get(0).getCart();
        List<Book> books = cart.getBooks();
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

    //Feature: Must be able to delete a book from the shopping cart instance for that user.
    //test curl: curl -X POST localhost:8080/cart/removeBook -d userName=testUserName -d bookIsbn=5780
    @RequestMapping (path = "/removeBook")    //Map *only* POST requests
    public @ResponseBody String removeBook (@RequestParam String userName, @RequestParam String bookIsbn)
    {
        List<User> users = userRepository.findByuserName(userName);
        List<Book> books = bookRepository.findBybookIsbn(bookIsbn);
        if(users.isEmpty()) return "Username does not exist.";
        else if(books.isEmpty()) return "Book does not exist.";
        else{
            Cart cart = users.get(0).getCart();
            if(cart.getBooks().size() <= 0) return "There are no books in your shopping cart";
            else{
                cart.removeBook(books.get(0));
                return "Book has been removed";
            }
        }
    }

    //test curl: curl -X GET localhost:8080/cart/allCarts
    //cmd gives infinitely recursive text of carts, needs fixing
    @RequestMapping (path = "/allCarts")
    public @ResponseBody Iterable<Cart> getAllCarts () { return cartRepository.findAll(); }
}
