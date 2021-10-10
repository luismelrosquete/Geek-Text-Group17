package com.example.geektext;

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
    //test curl: curl localhost:8080/cart/updateCart -d userName=testUserName -d bookIsbn=5780
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
            //Need code that adds the book into the specific shopping cart here
            cart.setQuantity(cart.getQuantity() + 1); //adding 1 doesnt seem to increment the amount of books in the cart?
            return "Cart has been updated";
        }
    }

    //Feature: Must be able to retrieve the list of book(s) in the shopping cart.
    //test curl: curl localhost:8080/cart/retrieveCart -d userName=testUserName
    @RequestMapping (path = "/retrieveCart")    //Map *only* POST requests
    public @ResponseBody String retrieveCart (@RequestParam String userName)
    {
        List<User> users = userRepository.findByuserName(userName);
        if(users.isEmpty()) return "Username does not exist.";
        else {
            Cart cart = users.get(0).getCart();
            List<Book> books = cart.getBooks();
            String out = "";
            for (int i = 0; i < books.stream().count(); i++)
                out += books.get(i).toString() + ((i + 1 != books.stream().count()) ? "\n" : "");
            return out;
        }
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
            if(cart.getQuantity() <= 0) return "There are no books in your shopping cart";
            else{
                cart.setQuantity(cart.getQuantity() - 1); //decrementing the cart doesnt seem to work similarly to updateCart
                //Need code that will remove the book from the specific shopping cart similar to updateCart here
                return "Book has been removed";
            }
        }
    }

    //test curl: curl localhost:8080/cart/allCarts
    //I'm not even sure if this is necessary to add
    @RequestMapping (path = "/allCarts")
    public @ResponseBody Iterable<Cart> getAllCarts () { return cartRepository.findAll(); }
}
