package com.sb.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.sb.messaging.MessageReceiver;

@Configuration
public class MessageConfiguration {

	private final String defalult_url="tcp://localhost:61616";
	private String ORDER_QUEUE="order_queue";
	private String ORDER_RESPONSE_QUEUE="order_response_queue";
	

    @Autowired
    MessageReceiver messageReceiver;
	
	@Bean
	public ConnectionFactory connectionFactory(){
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(defalult_url);
	    connectionFactory.setTrustedPackages(Arrays.asList("com.sb"));
        return  connectionFactory;
	}
	
	@Bean
	public ConnectionFactory cachedConnectionFactory(){
		CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}
	
	@Bean
	public MessageListenerContainer getContainer(){
		DefaultMessageListenerContainer container=new DefaultMessageListenerContainer();
		container.setConnectionFactory(cachedConnectionFactory());
		container.setDestinationName(ORDER_RESPONSE_QUEUE);
		container.setMessageListener(messageReceiver);
		return container;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate=new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestinationName(ORDER_QUEUE);
		return jmsTemplate;
	}
	
	@Bean
	MessageConverter convertor(){
		return new SimpleMessageConverter();
	}
	
}
