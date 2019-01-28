package com.kamsoft.invoice.invoice_converter;

import java.util.Date;

public class Head {
	
	private Date dateOfInvoice;
	private Date dateOfPayment;
	private int supplierID;
	private int receiverID;
	
	public Head(Date dateOfInvoice, Date dateOfPayment, int supplierID, int receiverID) {
		super();
		this.dateOfInvoice = dateOfInvoice;
		this.dateOfPayment = dateOfPayment;
		this.supplierID = supplierID;
		this.receiverID = receiverID;
	}
	public Date getDateOfInvoice() {
		return dateOfInvoice;
	}
	public void setDateOfInvoice(Date dateOfInvoice) {
		this.dateOfInvoice = dateOfInvoice;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	
}
