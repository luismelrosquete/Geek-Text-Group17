package com.example.geektext;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reviews
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewID;
    private String bookCode;
    private Integer userID;
    private Double rating;
    private String comment;
    private Date datestamp;
    //todo: add book (relational)

    //SETTERS
    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setDatestamp(Date datestamp) {
        this.datestamp = datestamp;
    }
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
    public void setUserID(Integer userID){
        this.userID = userID;
    }

    //GETTERS
    public Integer getReviewID() {
        return reviewID;
    }
    public Double getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }
    public Date getDatestamp() {
        return datestamp;
    }
    public String getBookCode() {
        return bookCode;
    }
    public Integer getUserID() {
        return userID;
    }
}
