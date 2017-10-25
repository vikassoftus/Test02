package com.centrica.test;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InputChannel.class)
public class MessageHandler {
	
    @Bean
    IntegrationFlow greetingsFlow(InputChannel channel) {
        return IntegrationFlows.from(channel.input())
                .handle(String.class, (payload, headers) -> {
                    System.out.println(payload);
                    return null;
                })
                .get();
    }
    
}
