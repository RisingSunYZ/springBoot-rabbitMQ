package com.example.demo;

import com.example.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
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
	public void send() throws Exception{
//		Map<String,String> map = new HashMap<>();
//		map.put("firstName","yang");
//		map.put("lastName","zhao");
//		rabbitTemplate.convertAndSend("testDirect",map);
//		rabbitTemplate.convertAndSend("yz",new Student("yangzhao","男"));

		//direct
//		CorrelationData correlationData = new CorrelationData();
//		correlationData.setId(System.currentTimeMillis() + "");
//		rabbitTemplate.convertAndSend("yzExchange.direct","yz.zwl",new Student("yangzhao111","男"));
//		rabbitTemplate.convertAndSend("zwl.yz",new Student("yangzhao","男"),correlationData);

//fanout
		CorrelationData correlationData = new CorrelationData();
		correlationData.setId(System.currentTimeMillis() + "");
		rabbitTemplate.convertAndSend("yzExchange.fanout","yz.zwl",new Student("yangzhao","男"),correlationData);

//topic
//		rabbitTemplate.convertAndSend("yzExchange.topic","yz.zwl",new Student("yangzhao","男"));
//		rabbitTemplate.convertAndSend("yzExchange.topic","zwl.news",new Student("yangzhao","男"));

		Thread.currentThread().join();
	}

	@Test
	public void receive() {
//		System.out.println(rabbitTemplate.receiveAndConvert("testDirect"));
	}

	@Test
	public void init() {
//		amqpAdmin.declareExchange(new DirectExchange("yzExchange.direct"));
//		amqpAdmin.declareExchange(new FanoutExchange("yzExchange.fanout"));
//		amqpAdmin.declareExchange(new TopicExchange("yzExchange.topic"));
//
//		amqpAdmin.declareQueue(new Queue("yz.zwl",true));
//		amqpAdmin.declareQueue(new Queue("zwl.yz",true));
//		amqpAdmin.declareQueue(new Queue("yz.news",true));
//		amqpAdmin.declareQueue(new Queue("yz.china",true));
//		amqpAdmin.declareQueue(new Queue("zwl.news",true));

//		amqpAdmin.deleteExchange("yzExchange.direct");
//		amqpAdmin.deleteExchange("yzExchange.fanout");
//		amqpAdmin.deleteExchange("yzExchange.topic");

//		amqpAdmin.deleteQueue("yz.zwl");
//		amqpAdmin.deleteQueue("zwl.yz");
//		amqpAdmin.deleteQueue("yz.news");
//		amqpAdmin.deleteQueue("yz.china");
//		amqpAdmin.deleteQueue("zwl.news");



//direct
//		amqpAdmin.declareBinding(new Binding("yz.zwl",Binding.DestinationType.QUEUE,"yzExchange.direct","yz.zwl",null));
//		amqpAdmin.declareBinding(new Binding("zwl.yz",Binding.DestinationType.QUEUE,"yzExchange.direct","zwl.yz",null));
//		amqpAdmin.declareBinding(new Binding("yz.news",Binding.DestinationType.QUEUE,"yzExchange.direct","yz.news",null));

		//fanout
		amqpAdmin.declareBinding(new Binding("yz.zwl",Binding.DestinationType.QUEUE,"yzExchange.fanout","yz.zwl",null));
		amqpAdmin.declareBinding(new Binding("zwl.yz",Binding.DestinationType.QUEUE,"yzExchange.fanout","zwl.yz",null));
		amqpAdmin.declareBinding(new Binding("yz.news",Binding.DestinationType.QUEUE,"yzExchange.fanout","yz.news",null));

//		amqpAdmin.declareBinding(new Binding("zwl.yz",Binding.DestinationType.QUEUE,"yzExchange.topic","zwl.*",null));
//		amqpAdmin.declareBinding(new Binding("yz.zwl",Binding.DestinationType.QUEUE,"yzExchange.topic","*.zwl",null));
//		amqpAdmin.declareBinding(new Binding("yz.news",Binding.DestinationType.QUEUE,"yzExchange.topic","#.news",null));

	}

}

