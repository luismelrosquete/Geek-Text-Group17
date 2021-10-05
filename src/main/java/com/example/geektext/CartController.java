package com.example.geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.geektext.BookController;
import com.example.geektext.Book;
import com.example.geektext.BookRepository;

import java.util.List;
import java.util.Arrays;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/cart")    //URL's start with "/user" after application path
public class CartController {

    @Autowired  //get the bean called TestRepository    //auto gen'd by spring, used to handle data
    private CartRepository cartRepository;
    private BookRepository bookRepository;

    /* Temporarily commented out as this user story is used in the UserController class
    //Feature: Must be able to create a shopping cart instance for a user. Shopping cart must belong to a user.
    //test curl: curl localhost:8080/cart/newCart -d quantity=1
    @PostMapping (path = "/newCart")    //Map *only* POST requests
    public @ResponseBody String newCart ()
    {
        //adding a quantity param to cart isnt really making much sense to me so it might be something I
        //end up removing and just having the cart class take vars primarily from book and maybe user
        Cart cart = new Cart();
        cart.setQuantity(0);
        cartRepository.save(cart);
        return "cart added";
    }
    */

    //Feature: Must be able to update the shopping cart with a book.
    //test curl: curl localhost:8080/cart/updateCart -d quantity=1
    @PostMapping (path = "/updateCart")    //Map *only* POST requests
    public @ResponseBody String updateCart (@RequestParam String cartId, @RequestParam String bookIsbn, @RequestParam Integer quantity)
    {
        List<Cart> carts = cartRepository.findBycartId(cartId);
        List<Book> books = bookRepository.findBybookIsbn(bookIsbn);
        Cart cart = carts.get(0);
        Book book = books.get(0);
        cart.setQuantity(cart.getQuantity()+1);
        return "Cart has been updated";
    }


    //Feature: Must be able to retrieve the list of book(s) in the shopping cart.
    //test curl: curl localhost:8080/cart/retrieveCart -d cartId=25
    @PostMapping (path = "/retrieveCart")    //Map *only* POST requests
    public @ResponseBody String retrieveCart (@RequestParam String cartId)
    {
        Cart cart = cartRepository.findBycartId(cartId).get(0);
        List<Book> books = cart.getBooks();
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? "\n" : "");
        return out;
    }


    //Feature: Must be able to delete a book from the shopping cart instance for that user.
    @PostMapping (path = "/deleteBook")    //Map *only* POST requests
    public @ResponseBody String deleteBook (@RequestParam String bookName, @RequestParam Integer quantity)
    {
        //making sure a book is in the cart before removing
        if(quantity <= 0) return "There are no books in your shopping cart";
        return "List of Books: " +bookName;
    }

    //test curl: curl localhost:8080/cart/allCarts
    //I'm not even sure if this is necessary to add
    @RequestMapping (path = "/allCarts")
    public @ResponseBody Iterable<Cart> getAllCarts () { return cartRepository.findAll(); }
}
