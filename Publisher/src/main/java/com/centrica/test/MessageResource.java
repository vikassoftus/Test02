package com.centrica.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@EnableBinding(OutputChannel.class)
public class MessageResource {
	
	@Autowired
	private OutputChannel channel;

    @Autowired
    public MessageResource(OutputChannel channel) {
        this.channel = channel;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/sendMessage/{message}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
    	System.out.println(message);
        Message<String> msg = MessageBuilder.withPayload(message).build();
        try {
        	channel.output().send(msg);
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
