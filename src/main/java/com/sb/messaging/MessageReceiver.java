package com.sb.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.sb.model.InventoryResponse;

@Component
public class MessageReceiver implements MessageListener {

	@Autowired
	MessageConverter messageConvertor;
	
	static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	public void onMessage(Message message) {		
		logger.info("enter in on message");
		try {
			InventoryResponse inventory=(InventoryResponse)messageConvertor.fromMessage(message);
			logger.info("message is received"+inventory.getProductId()+"---"+inventory.getRetunCode()+"---"+inventory.getComment());
		} catch (MessageConversionException e) {		
			e.printStackTrace();
		} catch (JMSException e) { 
			e.printStackTrace();
		}
	}

}
