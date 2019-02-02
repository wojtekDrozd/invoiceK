package com.kamsoft.invoice.invoice_converter;

import java.text.SimpleDateFormat;
import java.util.List;

public abstract class InvoiceWriter {

	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public abstract void writeFile(Invoice invoice);

	public void writeAll(List<Invoice> invoiceList) {
		for (Invoice invoice : invoiceList) {
			writeFile(invoice);
		}
	}

}
