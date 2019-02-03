package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public class Invoice {

	private String invoiceFileName;
	private Head head;
	private List<Position> positionsList;

	public Invoice(String invoiceFileName, Head head, List<Position> positionsList) {
		super();
		this.invoiceFileName = invoiceFileName;
		this.head = head;
		this.positionsList = positionsList;
	}

	public List<Position> getPositionsList() {
		return positionsList;
	}

	public void setPositionsList(List<Position> positionsList) {
		this.positionsList = positionsList;
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
