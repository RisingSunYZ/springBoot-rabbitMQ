package com.example.demo.service;

import com.example.demo.model.Student;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @RabbitListener(queues = "yz.zwl")
    public void receiveDirect(Student student, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception{
        try{
            channel.basicAck(deliveryTag,false);
            System.out.println("yz.zwl消费消息");
            System.out.println(student);
        }catch (Exception e){
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();
        }



    }

    @RabbitListener(queues = "zwl.yz")
    public void receiveDirect1(Student student, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, @Header("amqp_redelivered") boolean redelivered, Channel channel) throws Exception{
        try{
            if(1 == deliveryTag || 2 == deliveryTag){
                throw new Exception();
            }
            channel.basicAck(deliveryTag,false);
            System.out.println("zwl.yz消费消息");
            System.out.println(student);
        }catch (Exception e){
            System.out.println(redelivered);
            if (redelivered)
            {
                System.out.println("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(deliveryTag, true); // 拒绝消息
            }else{
                System.out.println("消息即将再次返回队列处理...");
                // 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
                channel.basicNack(deliveryTag, false, true); // requeue为是否重新回到队列
            }
            e.printStackTrace();

        }
    }

    @RabbitListener(queues = "yz.news")
    public void receiveDirect2(Student student, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception{
        try{
            // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
            channel.basicAck(deliveryTag,false);
            System.out.println("yz.news消费消息");
            System.out.println(student);
        }catch (Exception e){
            // 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();

        }
    }

}
