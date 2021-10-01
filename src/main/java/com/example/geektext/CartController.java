package com.example.geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.geektext.BookController;
import com.example.geektext.Book;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/cart")    //URL's start with "/user" after application path
public class CartController {

    @Autowired  //get the bean called TestRepository    //auto gen'd by spring, used to handle data
    private CartRepository CartRepository;

    //Feature: Must be able to create a shopping cart instance for a user. Shopping cart must belong to a user.
    //test curl: curl localhost:8080/cart/newCart -d quantity=1
    @PostMapping (path = "/newCart")    //Map *only* POST requests
    public @ResponseBody String newCart ()
    {
        /*
        adding a quantity param to cart isnt really making much sense to me so it might be something I
        end up removing and just having the cart class take vars primarily from book and maybe user
        */
        Cart cart = new Cart();
        cart.setQuantity(0);
        CartRepository.save(cart);
        return "cart added";
    }


    //Feature: Must be able to update the shopping cart with a book.
    @PostMapping (path = "/updateCart")    //Map *only* POST requests
    public @ResponseBody String updateCart (@RequestParam String CartId, @RequestParam String bookId, @RequestParam Integer quantity)
    {
        return "Cart has been updated";
    }


    //Feature: Must be able to retrieve the list of book(s) in the shopping cart.
    //test curl: curl localhost:8080/cart/retrieveCart -d bookName=Dune -d quantity=1
    @PostMapping (path = "/retrieveCart")    //Map *only* POST requests
    public @ResponseBody String retrieveCart (@RequestParam String bookName, @RequestParam Integer quantity,
                                              @RequestParam(required = false) Double price)
    {
        //making sure a book is in
        if(quantity <= 0) return "There are no books in your shopping cart";
        return "List of Books: " +bookName; //planning on adding an array that can
    }


    //Feature: Must be able to delete a book from the shopping cart instance for that user.
    @PostMapping (path = "/deleteBook")    //Map *only* POST requests
    public @ResponseBody String deleteBook (@RequestParam String bookName, @RequestParam Integer quantity,
                                            @RequestParam(required = false) Double price)
    {
        //making sure a book is in the cart before removing
        if(quantity <= 0) return "There are no books in your shopping cart";
        return "List of Books: " +bookName;
    }

    //test curl: curl localhost:8080/cart/allCarts
    //I'm not even sure if this is necessary to add
    @RequestMapping (path = "/allCarts")
    public @ResponseBody Iterable<Cart> getAllCarts () { return CartRepository.findAll(); }
}
