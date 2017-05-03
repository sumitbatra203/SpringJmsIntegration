package com.sb.model;

import java.io.Serializable;

public class InventoryResponse implements Serializable{

	private String productId;
	private String retunCode;
	private String comment;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRetunCode() {
		return retunCode;
	}
	public void setRetunCode(String retunCode) {
		this.retunCode = retunCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
