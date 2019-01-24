package com.example.demo;

import com.example.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void send() {
//		Map<String,String> map = new HashMap<>();
//		map.put("firstName","yang");
//		map.put("lastName","zhao");
//		rabbitTemplate.convertAndSend("testDirect",map);
//		rabbitTemplate.convertAndSend("yz",new Student("yangzhao","男"));
//		rabbitTemplate.convertAndSend("yzExchange.fanout","",new Student("yangzhao111","男"));
		rabbitTemplate.convertAndSend("yzExchange.topic","yz.zwl",new Student("yangzhao111","男"));
	}

	@Test
	public void receive() {
//		System.out.println(rabbitTemplate.receiveAndConvert("testDirect"));
	}

	@Test
	public void init() {
//		amqpAdmin.declareExchange(new DirectExchange("yzExchange.direct"));
//		amqpAdmin.declareExchange(new DirectExchange("yzExchange.fanout"));
//		amqpAdmin.declareExchange(new DirectExchange("yzExchange.topic"));
//
//		amqpAdmin.declareQueue(new Queue("yz.zwl",true));
//		amqpAdmin.declareQueue(new Queue("zwl.yz",true));
//		amqpAdmin.declareQueue(new Queue("yz",true));
//		amqpAdmin.declareQueue(new Queue("zwl",true));

//		amqpAdmin.deleteExchange("yzExchange.direct");
//		amqpAdmin.deleteExchange("yzExchange.fanout");
//		amqpAdmin.deleteExchange("yzExchange.topic");
//
//		amqpAdmin.deleteQueue("yz.zwl");
//		amqpAdmin.deleteQueue("zwl.zwl");
//		amqpAdmin.deleteQueue("yz");
//		amqpAdmin.deleteQueue("zwl");


//		amqpAdmin.declareBinding(new Binding("yz",Binding.DestinationType.QUEUE,"yzExchange.direct","yz",null));
//		amqpAdmin.declareBinding(new Binding("yz",Binding.DestinationType.QUEUE,"yzExchange.fanout","",null));
//		amqpAdmin.declareBinding(new Binding("zwl.yz",Binding.DestinationType.QUEUE,"yzExchange.fanout","",null));

		amqpAdmin.declareBinding(new Binding("yz.zwl",Binding.DestinationType.QUEUE,"yzExchange.topic","*.zwl",null));
		amqpAdmin.declareBinding(new Binding("zwl.yz",Binding.DestinationType.QUEUE,"yzExchange.topic","#.zwl",null));
		amqpAdmin.declareBinding(new Binding("zwl",Binding.DestinationType.QUEUE,"yzExchange.topic","zwl",null));
		amqpAdmin.declareBinding(new Binding("yz",Binding.DestinationType.QUEUE,"yzExchange.topic","yz",null));


	}

}

