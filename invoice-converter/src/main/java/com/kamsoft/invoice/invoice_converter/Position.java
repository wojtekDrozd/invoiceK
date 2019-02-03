package com.kamsoft.invoice.invoice_converter;

public class Position {

	private String productName;
	private long productID;
	private int quantity;
	private double netUnitPrice;
	private double taxVatValue;

	public Position(String productName, long productID, int quantity, double netUnitPrice, double taxVatValue) {
		super();
		this.productName = productName;
		this.productID = productID;
		this.quantity = quantity;
		this.netUnitPrice = netUnitPrice;
		this.taxVatValue = taxVatValue;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getNetUnitPrice() {
		return netUnitPrice;
	}

	public void setNetUnitPrice(double netUnitPrice) {
		this.netUnitPrice = netUnitPrice;
	}

	public double getTaxVatValue() {
		return taxVatValue;
	}

	public void setTaxVatValue(double taxVatValue) {
		this.taxVatValue = taxVatValue;
	}

}
