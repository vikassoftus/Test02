package com.centrica.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RabbitListener(queues="hello")
public class SubscriberApplication {

	@RabbitHandler
	public void recieve(String in) {
		System.out.println("Message Recieved =" +  in);
	}
	public static void main(String[] args) {
		SpringApplication.run(SubscriberApplication.class, args);
	}
}
