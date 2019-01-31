package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public class Invoice {
	
	private String invoiceFileName;
	private Head head;
	private List<Product> productList;
	
	public Invoice(String invoiceFileName, Head head, List<Product> productList) {
		super();
		this.invoiceFileName = invoiceFileName;
		this.head = head;
		this.productList = productList;
	}
	
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
	
	public String getInvoiceFileName() {
		return invoiceFileName;
	}

	public void setInvoiceFileName(String invoiceFileName) {
		this.invoiceFileName = invoiceFileName;
	}
}
