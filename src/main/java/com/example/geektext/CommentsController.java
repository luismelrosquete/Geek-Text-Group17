package com.example.geektext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Comment>> getAllReviews(
            @PathVariable("userName") String userName) {
        return service.getAllReviews(userName);
    }

    @RequestMapping(value="{businessName}", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<Comment>> getAllReviewsForBusiness(
            @PathVariable("userName") String userName){

        return service.getAllReviewsForBusiness(userName);
    }


    @RequestMapping(value="{businessName}", method = RequestMethod.POST)
    @ResponseBody
    public Response<Comment> addReviewForBusiness(
            @PathVariable("userName") String userName,
            @RequestBody Comment review){
        return service.addReviewForBusiness(review, userName);
    }


    @RequestMapping(value="id/{reviewId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Comment> getReview(
            @PathVariable("reviewId") Integer reviewId) {
        return service.getReview(reviewId);
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Response<Comment> updateReview(
            @PathVariable("userName") String userName,
            @RequestBody Comment review) {
        return service.updateReview(review);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Boolean> deleteReview(
            @PathVariable("userName") String userName,
            @RequestBody Comment review){
        return service.deleteReview(review);
    }
}