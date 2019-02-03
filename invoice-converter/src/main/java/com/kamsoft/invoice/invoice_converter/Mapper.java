package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public interface Mapper {
	
	public List<Invoice> mapProductIDs(List<Invoice> invoiceList);
}
