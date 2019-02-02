package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class App {
	public static void main(String[] args) {
		// TODO - rename Reader and product classes
		// TODO - write classes for writing txt and product ID Mappers
		// TODO - separate try catch for each operation

		try {
			// read csv files into list of Invoice objects
			InvoiceReader reader = new CsvInvoiceReader();
			List<File> fileList = reader.readFiles(new File(System.getProperty("user.dir")), "csv");
			List<Invoice> invoiceList = reader.createInvoiceList(fileList);

			// Map product IDs from csv to xml (supplier to ean)

			// write xml files using created list of Invoice objects with mapped product IDs
			InvoiceWriter writer = new XmlInvoiceWriter();
			writer.writeAll(invoiceList);

			// read xml files
			reader = new XmlInvoiceReader();
			fileList = reader.readFiles(new File(System.getProperty("user.dir")), "xml");
			invoiceList = reader.createInvoiceList(fileList);

			// Map product IDs from xml to ini (ean to receiver)

			// write ini files using list of Invoice objects with mapped productID
			writer = new IniInvoiceWriter();
			writer.writeAll(invoiceList);

		} catch (ParseException e) {
			System.out.println("Problem with parsing data - check data format");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
