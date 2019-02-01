package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public abstract class InvoiceWriter {
	
	public abstract void writeFile(Invoice invoice);
	
	public void writeAll(List<Invoice> invoiceList) {
		for (Invoice invoice : invoiceList) {
			writeFile(invoice);
		}
	}

}
