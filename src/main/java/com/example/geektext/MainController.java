package com.example.geektext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/demo")    //URL's start with "/demo" after application path
public class MainController
{
    // 	The preceding example explicitly specifies POST and GET for the two endpoints.
    // 	By default, @RequestMapping maps all HTTP operations.

    @Autowired  //get the bean called TestRepository    //auto gen'd by spring, used to handle data
    private UserRepository userRepository;

    //
    @PostMapping (path = "/add")    //Map *only* POST requests
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String email)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved user";
    }

    @GetMapping (path = "/all")
    public @ResponseBody Iterable<User> getAllTestEntities ()
    {
        //returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
