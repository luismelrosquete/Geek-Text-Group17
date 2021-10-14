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

    Integer quantity = 0;

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
            Cart cart = users.get(0).getCart(); //seems useless to have if not calling quantity through cart class (i.e. getQuantity)
            //Need code that adds the book into the specific shopping cart here
            Book book = books.get(0);
            book.getBookIsbn();
            quantity = quantity + 1; //does its job of incrementing quantity but could go wrong so this way of doing it might be temporary
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
    //test curl: curl localhost:8080/cart/deleteBook -d userName=testUserName -d bookIsbn=5780
    @RequestMapping (path = "/deleteBook")    //Map *only* POST requests
    public @ResponseBody String deleteBook (@RequestParam String userName, @RequestParam String bookIsbn)
    {
        List<User> users = userRepository.findByuserName(userName);
        List<Book> books = bookRepository.findBybookIsbn(bookIsbn);
        if(users.isEmpty()) return "Username does not exist.";
        else if(books.isEmpty()) return "Book does not exist.";
        //making sure a book is in the cart before removing
        else{
            Cart cart = users.get(0).getCart();
            if(quantity <= 0) return "There are no books in your shopping cart";
            else{
                quantity = quantity - 1; //decrementing the cart doesnt seem to work similarly to updateCart
                //Need code that will remove the book from the specific shopping cart similar to updateCart here
                return "Book has been removed";
            }
        }
    }

    //test curl: curl localhost:8080/cart/allCarts
    //requesting allCarts gives a really weird output so I wouldn't bother testing it
    @RequestMapping (path = "/allCarts")
    public @ResponseBody Iterable<Cart> getAllCarts () { return cartRepository.findAll(); }
}
