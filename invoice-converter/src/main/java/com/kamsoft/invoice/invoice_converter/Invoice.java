package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public class Invoice {
	
	private Head head;
	public Invoice(Head head, List<Product> productList) {
		super();
		this.head = head;
		this.productList = productList;
	}
	private List<Product> productList;
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
}
