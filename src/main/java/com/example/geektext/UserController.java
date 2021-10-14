package com.example.geektext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/user")    //URL's start with "/user" after application path
public class UserController
{
    // 	The preceding example explicitly specifies POST and GET for the two endpoints.
    // 	By default, @RequestMapping maps all HTTP operations.

    @Autowired  //get the bean called TestRepository    //auto gen'd by spring, used to handle data
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    //POST
    //test curl: curl -X POST localhost:8080/user/addUser -d userName=testUserName -d pw=pa$$w0rd -d email=email@provided.com -d fullName=FirstLast -d address=test
    //make sure to test removing optional parameters as well
    //Feature: Must be able to create a User with username(email), password and optional fields  (name, email address, home address)
    @PostMapping (path = "/addUser")    //Map *only* POST requests
    public @ResponseBody String addUser (@RequestParam String userName, @RequestParam String pw,
                                         @RequestParam(required = false) String email, @RequestParam(required = false) String fullName,
                                         @RequestParam(required = false) String address)
    {
        List<User> users = userRepository.findByuserName(userName);
        if(users.size() != 0){ return "Error: Username already in use"; }

        User user = new User();
        user.setUserName(userName);
        user.setUserPw(pw);
        if(email != null) //required = false was used for optional fields. needs to be tested for if they were put in or not
            user.setUserEmail(email);
        if(fullName != null)
            user.setUserFullName(fullName);
        if(address != null)
            user.setUserAddress(address);
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        userRepository.save(user);
        cartRepository.save(cart);
        return "Saved user";
    }

    //PUT
    //test curl: curl -X PUT localhost:8080/user/updateUser -d userName=testUserName -d fullName=NewName
    //Feature: Must be able to update the user and any of their fields except for mail
    @RequestMapping(path = "/updateUser")
    public @ResponseBody String updateUser (@RequestParam String userName, @RequestParam(required = false) String pw,
                                            @RequestParam(required = false) String fullName, @RequestParam(required = false) String address)
    {
        List<User> users = userRepository.findByuserName(userName);
        if(users.size() == 0){ return "Error: User does not exist"; }
        User user = users.get(0);

        //change vars of user obj
        if(pw != null) //need to check which optional value is to be updated
            user.setUserEmail(pw);
        if(fullName != null)
            user.setUserFullName(fullName);
        if(address != null)
            user.setUserAddress(address);

        //save the updated user:
        userRepository.save (user);
        //output msg
        return "Updated user";
    }

    //GET
    //test curl: curl -X GET localhost:8080/user/findUser?userName=testUserName
    //Feature: Must be able to retrieve a User Object and its fields by their username
    //@GetMapping (path = "/findUser")
    @RequestMapping(path = "/findUser")
    public ResponseEntity<User> getUser (@RequestParam String userName)
    {
        List<User> users = userRepository.findByuserName(userName);
        if (users.stream().count() > 0)
            return new ResponseEntity<>(users.get(0), HttpStatus.OK); //should theoretically only be one user with that username
        return null;
    }

    //POST
    //test curl: curl -X POST localhost:8080/user/addCreditCard -d userName=testUserName -d cardNumber=0987654321211234 -d cardSecurityPin=123 -d cardExpiryMonth=10 -d cardExpiryYear=21
    @RequestMapping(path = "/addCreditCard")
    public @ResponseBody String addCreditCard(@RequestParam String userName, @RequestParam String cardNumber,
                                              @RequestParam int cardSecurityPin, @RequestParam int cardExpiryMonth,
                                              @RequestParam int cardExpiryYear){
        List<User> users = userRepository.findByuserName(userName);
        if(users.size() == 0){ return "Error: User does not exist"; }
        User user = users.get(0);
        CreditCard card = new CreditCard();
        card.setCardNumber(cardNumber);
        card.setCardSecurityPin(cardSecurityPin);
        card.setCardExpiryMonth(cardExpiryMonth);
        card.setCardExpiryYear(cardExpiryYear);
        card.setUser(user);
        creditCardRepository.save(card);
        return "Saved Credit Card.";
    }

    //GET
    //test curl //curl -X GET localhost:8080/user/getCreditCards?userName=testUserName
    @RequestMapping(path = "/getCreditCards")
    public @ResponseBody String getCreditCards (@RequestParam String userName){
        List<User> users = userRepository.findByuserName(userName);
        if(users.size() == 0){ return "Error: User does not exist"; }
        List<CreditCard> cards = users.get(0).getCreditCards();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(cards);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Failed to return credit cards.";
    }

    //GET
    //test curl: curl -X GET localhost:8080/user/allUsers
    @GetMapping (path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers ()
    {
        //returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
