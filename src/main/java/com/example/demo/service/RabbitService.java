package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @RabbitListener(queues = "yz.zwl")
    public void receiveDirect(Student student){
        System.out.println("yz.zwl");
        System.out.println(student);
    }

    @RabbitListener(queues = "zwl.yz")
    public void receiveDirect1(Student student){
        System.out.println("zwl.yz");
        System.out.println(student);
    }

    @RabbitListener(queues = "yz.news")
    public void receiveDirect2(Student student){
        System.out.println("yz.news");
        System.out.println(student);
    }




}
