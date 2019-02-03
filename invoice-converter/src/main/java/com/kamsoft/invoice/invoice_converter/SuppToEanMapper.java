package com.kamsoft.invoice.invoice_converter;

import java.util.List;

public class SuppToEanMapper implements Mapper {

	public List<Invoice> mapProductIDs(List<Invoice> invoiceList) {

		for (Invoice invoice : invoiceList) {
			
			for (Position position : invoice.getPositionsList()) {
				position.setProductID(position.getProductID()+5900000000000L);
			}
		}
		
		return invoiceList;
	}

}
