package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.ini4j.Ini;

public class IniInvoiceWriter extends InvoiceWriter {

	@Override
	public void writeFile(Invoice invoice) {
		Ini ini = new Ini();
		int productNumber = 1;
		String productSectionName = "";
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		//add head section
		ini.put("NAGLOWEK", "data_w", invoice.getHead().getDateOfInvoice().toString());
		ini.put("NAGLOWEK", "data_p", invoice.getHead().getDateOfPayment().toString());
		ini.put("NAGLOWEK", "dostawca", invoice.getHead().getSupplierID());
		ini.put("NAGLOWEK", "odbiorca", invoice.getHead().getReceiverID());
		
		//add sections for each product
		for (Product productObj : invoice.getProductList()) {
			productSectionName = "POZYCJA NR "+Integer.toString(productNumber);
			ini.put(productSectionName, "nazwa", productObj.getProductName());
			ini.put(productSectionName, "towar", productObj.getProductID());
			ini.put(productSectionName, "ilosc", productObj.getQuantity());
			ini.put(productSectionName, "netto", productObj.getNetUnitPrice());
			ini.put(productSectionName, "vat", productObj.getTaxVatValue());
			productNumber+=1;
		}
		
		try {
			ini.store(new File(invoice.getInvoiceFileName()+".ini"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
