package com.example.demo.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setConfirmCallback(confirmCallback());
        template.setReturnCallback(returnCallback());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }


    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                   if(!ack){
                       System.out.println("消息唯一标识："+correlationData);
                       System.out.println("确认结果："+ack);
                       System.out.println("失败原因："+cause);
                   }

            }
        };
    }

    @Bean
    public RabbitTemplate.ReturnCallback returnCallback(){
        return new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("消息主体 message : "+message);
                System.out.println("消息主体 message : "+replyCode);
                System.out.println("描述："+replyText);
                System.out.println("消息使用的交换器 exchange : "+exchange);
                System.out.println("消息使用的路由键 routing : "+routingKey);
            }
        };
    }

}
