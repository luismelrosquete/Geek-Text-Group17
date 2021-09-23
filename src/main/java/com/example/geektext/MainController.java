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

    //USER
    //test curl: curl localhost:8080/demo/add -d userName=testUserName -d pw=pa$$w0rd -d email=email@provided.com -d fullName=FirstLast -d address=test
    @PostMapping (path = "/add")    //Map *only* POST requests
    public @ResponseBody String addUser (@RequestParam String userName, @RequestParam String pw,
                                         @RequestParam String email, @RequestParam String fullName,
                                         @RequestParam String address)
    {
        User user = new User();
        user.setUserName(userName);
        user.setUser_pw(pw);
        user.setUser_email(email);
        user.setUser_fullName(fullName);
        user.setUser_address(address);
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
