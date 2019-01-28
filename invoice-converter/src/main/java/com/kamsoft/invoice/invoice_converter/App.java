package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        InvoiceReader reader = new CsvInvoiceReader();
        List<File> fileList = reader.readFiles(new File(System.getProperty("user.dir")), "csv");
        try {
			List<Invoice> invoiceList = reader.createInvoiceList(fileList);
			for (Invoice invoice : invoiceList) {
				System.out.println(invoice.getHead().getDateOfInvoice());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
