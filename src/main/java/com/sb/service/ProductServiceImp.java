package com.sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sb.messaging.MessageSender;
import com.sb.model.Product;

@Service("productService")
public class ProductServiceImp implements ProductService {
	  static final Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);
	@Autowired
	MessageSender messageSender;
	public void sendProduct(Product product) {
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		logger.info("Application : sending order request {}", product);
	    messageSender.sendMessage(product);
	    logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");		
	}

}
