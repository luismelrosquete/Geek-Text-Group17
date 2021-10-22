package com.example.geektext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Response<Rating> getRating(
            @PathVariable("userName") String userName,
            @PathVariable("businessName") String businessName) {
        return service.getRating(userName, businessName);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<Rating> addReview(
            @PathVariable("userName") String userName){

        return service.addRating(userName);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Response<Rating> updateReview(
            @PathVariable("userName") String userName){
        return service.updateRating(userName);
    }
}
