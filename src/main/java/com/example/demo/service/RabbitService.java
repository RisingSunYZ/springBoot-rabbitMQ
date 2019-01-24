package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @RabbitListener(queues = "zwl")
    public void receiveDirect(Student student){
        System.out.println(student);
    }

    @RabbitListener(queues = "yz.zwl")
    public void receiveDirect1(Student student){
        System.out.println(student);
    }



}
