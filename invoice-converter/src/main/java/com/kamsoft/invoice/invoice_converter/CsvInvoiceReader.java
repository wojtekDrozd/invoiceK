package com.kamsoft.invoice.invoice_converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvInvoiceReader extends InvoiceReader {
	
	// set splitting character of csv file
	private String csvSplitBy = "\\|";

	// parse date formatted in given csv
	public Date parseDate(String stringDate) {
		Date dateOfInvoice = null;
		try {
			dateOfInvoice = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
		} catch (ParseException e) {
			System.out.println("Incorrect date format");
			e.printStackTrace();
		}
		return dateOfInvoice;
	}
	
	
	//creates single head object from list of strings
	public Head createHead(List<String> recordList) throws ParseException {
		String[] splittedRecord = recordList.get(0).split(csvSplitBy);
		Date dateOfInvoice = parseDate(splittedRecord[0]);
		Date dateOfPayment = parseDate(splittedRecord[1]);
		int supplierID = Integer.parseInt(splittedRecord[2]);
		int receiverID = Integer.parseInt(splittedRecord[3]);
		Head head = new Head(dateOfInvoice,dateOfPayment,supplierID,receiverID);
		return head;
	}

	//creates productList from list of strings
	public List<Product> createProductList(List<String> recordList) throws ParseException {
		List<Product> productList = new ArrayList<Product>();
		// for all but first record
		for (String string : recordList.subList(1, recordList.size())) {
			String[] splittedRecord = string.split(csvSplitBy);
			String productName = splittedRecord[0];
			int productID = Integer.parseInt(splittedRecord[1]);
			int quantity = Integer.parseInt(splittedRecord[2]);
			double netUnitPrice = Double.parseDouble(splittedRecord[3]);
			double taxVatValue = Double.parseDouble(splittedRecord[4]);
			Product product = new Product(productName,productID,quantity,netUnitPrice,taxVatValue);
			productList.add(product);
		}
		
		return productList;
	}
		

}
	

