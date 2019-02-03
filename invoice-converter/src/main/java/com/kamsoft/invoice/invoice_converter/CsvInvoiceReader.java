package com.kamsoft.invoice.invoice_converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvInvoiceReader extends InvoiceReader {

	// set splitting character of csv file
	private String csvSplitBy = "\\|";

	// this will extract lines from file
	public List<String> extractRecords(File file) {
		List<String> recordList = new ArrayList<String>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				recordList.add(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error reading file " + file.getName());
			e.printStackTrace();
		}

		return recordList;
	}

	// creates single head object from list of strings
	public Head createHead(File file) throws ParseException {
		List<String> recordList = extractRecords(file);
		String[] splittedRecord = recordList.get(0).split(csvSplitBy);
		Date dateOfInvoice = parseDate(splittedRecord[0]);
		Date dateOfPayment = parseDate(splittedRecord[1]);
		int supplierID = Integer.parseInt(splittedRecord[2]);
		int receiverID = Integer.parseInt(splittedRecord[3]);
		Head head = new Head(dateOfInvoice, dateOfPayment, supplierID, receiverID);
		return head;
	}

	// creates productList from list of strings
	public List<Position> createPositionsList(File file) throws ParseException {
		List<Position> positionsList = new ArrayList<Position>();
		List<String> recordList = extractRecords(file);
		// for all but first record
		for (String string : recordList.subList(1, recordList.size())) {
			String[] splittedRecord = string.split(csvSplitBy);
			String productName = splittedRecord[0];
			long productID = Long.parseLong(splittedRecord[1]);
			int quantity = Integer.parseInt(splittedRecord[2]);
			double netUnitPrice = Double.parseDouble(splittedRecord[3]);
			double taxVatValue = Double.parseDouble(splittedRecord[4]);
			Position position = new Position(productName, productID, quantity, netUnitPrice, taxVatValue);
			positionsList.add(position);
		}

		return positionsList;
	}

}
