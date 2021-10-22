package com.example.geektext;

//import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //class is a controller (MVC)
@RequestMapping(path = "/reviews")    //URL's start with "/book" after application path
public class ReviewsController
{
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private BookRepository bookRepository;

    //test curl //curl localhost:8080/reviews/addReview -d bookIsbn=5780 -d userID=0 -d rating=5 -d comment="test comment"
    @RequestMapping (path = "/addReview")
    public @ResponseBody String addReview(@RequestParam String bookIsbn, @RequestParam Integer userID,
                                          @RequestParam Double rating, @RequestParam String comment)
    {
        //find the book:
        var books = bookRepository.findBybookIsbn(bookIsbn);
        //if null/empty, exit
        if (books.stream().count() == 0 || books.get(0) == null)
            return "Cannot add rating to book with ISBN: \""+bookIsbn+"\". No rating added.";

        //TODO: confirm there isn't a duplicate userID with a duplicate bookIsbn before adding. Output msg if duplicate.

        //create new review:
        Reviews review = new Reviews();
        //fill fields
        review.setDatestamp(Date.from(Instant.now()));
        review.setComment(comment);
        review.setRating(rating);
        review.setUserID(userID);
        review.setBookCode(bookIsbn);
        //save review:
        reviewsRepository.save(review);

        //update avg rating for book
        var success = updateAvgRating(bookIsbn, rating);

        //return success/fail msg (just in-case)
        if (success)
            return "Added rating.";
        else
            return "Failed to add rating.";
    }

    //internal //method to update average rating for a book
    private Boolean updateAvgRating(String bookIsbn, Double rating)
    {
        //grab book with ISBN
        var books = bookRepository.findBybookIsbn(bookIsbn);
        //null/empty check
        if (books.stream().count() == 0 || books.get(0) == null)
            return false;

        //find ratings for book:
        var reviews = reviewsRepository.findReviewsByBookCode(bookIsbn);
        //null/empty check:
        if (reviews.stream().count() == 0)
            return false;

        //go through all reviews, get avg rating:
        Double avgRating = rating;
        int validRatings = (rating > 0) ? 1 : 0;    //for updating books without adding rating
        for (int i = 0; i < reviews.stream().count(); i++)
        {
            var r = reviews.get(i);
            if (r.getRating() > 0)
            {
                avgRating+= reviews.get(i).getRating();
                validRatings++;
            }
        }
        avgRating /= validRatings;

        //update avg rating for book:
        books.get(0).setBookAvgRating(avgRating);
        bookRepository.save(books.get(0));
        return true;
    }

    //public Double findAvg(Integer bookCode) {
    //    return reviewsRepository.pullAvg(bookCode).get(0);
    //}

    //curl test //curl localhost:8080/reviews/getReviews
    @RequestMapping (path = "/getReviews")
    public @ResponseBody List<Reviews> getAllReviews()
    {
        List<Reviews> reviews = new ArrayList<>();
        reviewsRepository.findByOrderByRatingDesc().forEach(reviews::add);
        return reviews;
    }

    //curl test //curl localhost:8080/reviews/updateAllRatings
    //UPDATES all book ratings
    @RequestMapping (path = "/updateAllRatings")
    public @ResponseBody String updateAllRatings ()
    {
        var books = bookRepository.findAll();
        for (Book book : books)
        {
            updateAvgRating(book.getBookIsbn(), 0.0);
        }
        return "Updated all book ratings.";
    }

    //GET
    //curl test //curl -X GET localhost:8080/reviews/allReviews
    //GETS all reviews
    @GetMapping(path = "/allReviews")
    public @ResponseBody Iterable<Reviews> getAllBooks ()
    {
        //returns a JSON/XML with all books
        return reviewsRepository.findAll();
    }
}