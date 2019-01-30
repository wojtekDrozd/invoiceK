package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	// TODO - rename Reader and product classess
    	// TODO - write classess for writning xml, writing ini, and product ID Mappers
        InvoiceReader reader = new CsvInvoiceReader();
        List<File> fileList = reader.readFiles(new File(System.getProperty("user.dir")), "csv");
        try {
			List<Invoice> invoiceList = reader.createInvoiceList(fileList);
			// test printout of invoices
			// TODO - implement toString methods for product and head objects
			for (Invoice invoice : invoiceList) {
				System.out.println(invoice.getHead().getDateOfPayment());
				System.out.println(invoice.getProductList().get(0).getProductName());
			}
		} catch (ParseException e) {
			System.out.println("Problem with parsing data - check data format");
			e.printStackTrace();
		}
    }
}
