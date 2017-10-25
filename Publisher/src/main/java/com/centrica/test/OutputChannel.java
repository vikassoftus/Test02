package com.centrica.test;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface OutputChannel {

	@Output
    MessageChannel output();
}
