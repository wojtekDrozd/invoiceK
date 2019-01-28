package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class InvoiceReader {
	
	//this will list files with chosen extension
	public List<File> readFiles(File directory, String extension){
		List<File> resultList = new ArrayList<File>();
		File[] fileList = directory.listFiles();
		String filePath = "";
		String fileExtension = "";
		
		for (File file : fileList) {
			filePath = file.getAbsolutePath();
			fileExtension = file.getAbsolutePath().substring(filePath.lastIndexOf(".")+1,filePath.length());
			if(extension.equals(fileExtension))
			resultList.add(file);
		}

		for (File file: resultList) {
			System.out.println(file.getName());
		}
		
		return resultList;
	}
	
	public abstract List<Invoice> createInvoiceList(List<File> fileList) throws ParseException;
}
