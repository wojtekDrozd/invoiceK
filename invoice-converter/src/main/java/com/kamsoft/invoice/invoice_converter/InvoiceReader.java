package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

public abstract class InvoiceReader {

	// this will create Head for single file read
	public abstract Head createHead(File file)
			throws ParseException, ParserConfigurationException, SAXException, IOException;

	// this will create list of Products(positions) from single file
	public abstract List<Product> createProductList(File file)
			throws ParseException, ParserConfigurationException, SAXException, IOException;

	// this will list files with chosen extension
	public List<File> readFiles(File directory, String extension) {
		List<File> resultList = new ArrayList<File>();
		File[] fileList = directory.listFiles();
		String filePath = "";
		String fileExtension = "";

		for (File file : fileList) {
			filePath = file.getAbsolutePath();
			fileExtension = file.getAbsolutePath().substring(filePath.lastIndexOf(".") + 1, filePath.length());
			if (extension.equals(fileExtension))
				resultList.add(file);
		}

		return resultList;
	}

	// parse date formatted in given way
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

	// this will create list of invoices from all files read
	public List<Invoice> createInvoiceList(List<File> fileList)
			throws ParseException, ParserConfigurationException, SAXException, IOException {
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		for (File file : fileList) {
			// List<String> records = extractRecords(file);
			Head head = createHead(file);
			List<Product> productList = createProductList(file);
			String invoiceFileName = FilenameUtils.removeExtension(file.getName());
			if (head != null) {
				Invoice invoice = new Invoice(invoiceFileName, head, productList);
				invoiceList.add(invoice);
			}
		}
		return invoiceList;
	}

}
