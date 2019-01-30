package com.kamsoft.invoice.invoice_converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class InvoiceReader {
	
	// this will create Head for single file read
	public abstract Head createHead(List<String> recordList) throws ParseException;
	
	// this will create list of Products(positions) from single file
	public abstract List<Product> createProductList(List<String> recordList) throws ParseException;
	
	//this will list files with chosen extension
	public List<File> readFiles(File directory, String extension){
		List<File> resultList = new ArrayList<File>();
		File[] fileList = directory.listFiles();
		String filePath = "";
		String fileExtension = "";
		
		for (File file : fileList) {
			filePath = file.getAbsolutePath();
			fileExtension = file.getAbsolutePath().substring(filePath.lastIndexOf(".")+1,filePath.length());
			if(extension.equals(fileExtension))
			resultList.add(file);
		}

		for (File file: resultList) {
			System.out.println(file.getName());
		}
		
		return resultList;
	}
	
	// this will extract lines from file
	public List<String> extractRecords(File file) {
		List<String> recordList = new ArrayList<String>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));			
				while ((line = br.readLine()) != null) {
					recordList.add(line);			
				}		
			br.close();		
		} catch (IOException e) {
			System.out.println("Error reading file " + file.getName());
			e.printStackTrace();}
		
		return recordList;
	}
	
	// this will create list of invoices from all files read
	public List<Invoice> createInvoiceList(List<File> fileList) throws ParseException{
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		for (File file : fileList) {
			List<String> records = extractRecords(file);
			Head head = createHead(records);
			List<Product> productList = createProductList(records);
			Invoice invoice = new Invoice(head,productList);
			invoiceList.add(invoice);
		}
		return invoiceList;
	}
	

}
