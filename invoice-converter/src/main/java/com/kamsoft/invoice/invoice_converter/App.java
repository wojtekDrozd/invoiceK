package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	// TODO - rename Reader and product classes
    	// TODO - write classes for reading xml, and product ID Mappers
    	// TODO - format date in better way
        InvoiceReader reader = new CsvInvoiceReader();
        List<File> fileList = reader.readFiles(new File(System.getProperty("user.dir")), "csv");
        try {
			List<Invoice> invoiceList = reader.createInvoiceList(fileList);
			InvoiceWriter writer = new XmlInvoiceWriter();
			writer.writeAll(invoiceList);
			writer = new IniInvoiceWriter();
			writer.writeAll(invoiceList);
		} catch (ParseException e) {
			System.out.println("Problem with parsing data - check data format");
			e.printStackTrace();
		}
    }
}
