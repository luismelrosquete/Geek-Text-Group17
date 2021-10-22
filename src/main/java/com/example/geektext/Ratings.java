package com.example.geektext;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Ratings {

    @Id
    private int id;
    private Date createdDate;
    private int userId;

    @Override
    public String toString() {
        return "Rating [id=" + id + ", createdDate=" + createdDate + ", ratingValue=" + ", userId="
                + userId + "]";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


}