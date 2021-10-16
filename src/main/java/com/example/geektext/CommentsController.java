package com.example.geektext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Controller //class is a controller (MVC)
@RequestMapping (path = "/comments")


public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    public @ResponseBody String addComment()
    {
        List<Comment> comments = commentRepository.addComment();
        //JSON out:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(comments);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //failure message
        return "ERROR";

    }

}
