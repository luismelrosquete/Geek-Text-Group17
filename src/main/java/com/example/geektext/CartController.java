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
    private BookRepository bookRepository;

    //Feature: Must be able to update the shopping cart with a book.
    //test curl: curl localhost:8080/cart/updateCart -d quantity=1
    @PostMapping (path = "/updateCart")    //Map *only* POST requests
    public @ResponseBody String updateCart (@RequestParam Long cartId, @RequestParam String bookIsbn, @RequestParam Integer quantity)
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
    @RequestMapping (path = "/retrieveCart")    //Map *only* POST requests
    public @ResponseBody String retrieveCart (@RequestParam Long cartId)
    {
        Cart cart = cartRepository.findBycartId(cartId).get(0);
        List<Book> books = cart.getBooks();
        String out = "";
        for (int i = 0; i < books.stream().count(); i++)
            out += books.get(i).toString() + ((i+1 != books.stream().count()) ? "\n" : "");
        return out;
    }


    //Feature: Must be able to delete a book from the shopping cart instance for that user.
    @RequestMapping (path = "/deleteBook")    //Map *only* POST requests
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
