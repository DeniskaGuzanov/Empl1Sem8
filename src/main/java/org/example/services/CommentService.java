package org.example.services;

import org.example.aspects.ToLog;
import org.example.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;



@Component
public class CommentService {

    private Logger logger = Logger.getLogger(CommentService.class.getName());

    @ToLog
    public String publishComment(Comment comment) {
        System.out.println("Publishing comment:" + comment.getText());
        return "SUCCESS";
    }

//    public void method1(){
//        System.out.println("Вызван метод 1");
//    }

    public Logger getLogger() {
        return logger;

    }
}