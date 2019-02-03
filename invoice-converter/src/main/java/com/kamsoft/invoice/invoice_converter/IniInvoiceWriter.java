package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

public class IniInvoiceWriter extends InvoiceWriter {

	@Override
	public void writeFile(Invoice invoice) {
		Ini ini = new Ini();
		int positionNumber = 1;
		String positionSectionName = "";

		// add head section
		ini.put("NAGLOWEK", "data_w", dateFormat.format(invoice.getHead().getDateOfInvoice()));
		ini.put("NAGLOWEK", "data_p", dateFormat.format(invoice.getHead().getDateOfPayment()));
		ini.put("NAGLOWEK", "dostawca", invoice.getHead().getSupplierID());
		ini.put("NAGLOWEK", "odbiorca", invoice.getHead().getReceiverID());

		// add sections for each product
		for (Position positionObj : invoice.getPositionsList()) {
			positionSectionName = "POZYCJA NR " + Integer.toString(positionNumber);
			ini.put(positionSectionName, "nazwa", positionObj.getProductName());
			ini.put(positionSectionName, "towar", positionObj.getProductID());
			ini.put(positionSectionName, "ilosc", positionObj.getQuantity());
			ini.put(positionSectionName, "netto", positionObj.getNetUnitPrice());
			ini.put(positionSectionName, "vat", positionObj.getTaxVatValue());
			positionNumber += 1;
		}

		try {
			ini.store(new File(invoice.getInvoiceFileName() + ".ini"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
