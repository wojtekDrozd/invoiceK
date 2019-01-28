package com.kamsoft.invoice.invoice_converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvInvoiceReader extends InvoiceReader {
	
	private String line = "";
	private String csvSplitBy = "\\|";

	@Override
	public List<Invoice> createInvoiceList(List<File> fileList) throws ParseException {
		
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		for (File file : fileList) {
			Head head = new Head(new SimpleDateFormat("yyyy/MM/dd").parse("1901/01/01"),
					new SimpleDateFormat("yyyy/MM/dd").parse("1901/01/01"),0,0);

			List<Product> productList = new ArrayList<Product>();
			Invoice invoice = new Invoice(head, productList);
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				int count = 0;
				
					while ((line = br.readLine()) != null) {
						count +=1;
						String[] record = line.split(csvSplitBy);
						// creating Head object
						if (count == 1){
							 head = new Head(new SimpleDateFormat("yyyy/MM/dd").parse(record[0]),
									new SimpleDateFormat("yyyy/MM/dd").parse(record[1]),
									Integer.parseInt(record[2]),
									Integer.parseInt(record[3]));
						}
						else {
							Product product = new Product(record[0],
									Integer.parseInt(record[1]),
									Integer.parseInt(record[2]),
									Double.parseDouble(record[3]),
									Double.parseDouble(record[4]));
							productList.add(product);
						}
						System.out.println(record[0]+" "+record[1]+" "+record[2]+" "+record[3]);
						
					}		
				br.close();	
				
				invoice = new Invoice(head, productList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		invoiceList.add(invoice);	
		}
		
		return null;
	}

}
