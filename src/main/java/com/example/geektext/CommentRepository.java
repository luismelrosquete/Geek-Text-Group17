package com.example.geektext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.xml.stream.events.Comment;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List <Comment> addComment();

}

