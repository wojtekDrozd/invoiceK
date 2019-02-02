package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlInvoiceReader extends InvoiceReader {

	private NodeList createNodeList(File file, String nodeName)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(nodeName);

		return nList;

	}

	@Override
	public Head createHead(File file) throws ParseException, ParserConfigurationException, SAXException, IOException {
		try {
			NodeList nList = createNodeList(file, "naglowek");
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			Date dateOfInvoice = parseDate(eElement.getElementsByTagName("data_w").item(0).getTextContent());
			Date dateOfPayment = parseDate(eElement.getElementsByTagName("data_p").item(0).getTextContent());
			int supplierID = Integer.parseInt(eElement.getElementsByTagName("dostawca").item(0).getTextContent());
			int receiverID = Integer.parseInt(eElement.getElementsByTagName("odbiorca").item(0).getTextContent());
			Head head = new Head(dateOfInvoice, dateOfPayment, supplierID, receiverID);
			return head;
		} catch (NullPointerException e) {
			System.out.println("incorrect file format " + file.getName());
		}

		return null;
	}

	// this needs to be fixed to read next products
	@Override
	public List<Product> createProductList(File file)
			throws ParseException, ParserConfigurationException, SAXException, IOException {
		NodeList nList = createNodeList(file, "pozycja");
		List<Product> productList = new ArrayList<Product>();
		Node nNode;

		for (int i = 0; i < nList.getLength(); i++) {
			nNode = nList.item(i);
			Element eElement = (Element) nNode;
			String productName = eElement.getElementsByTagName("nazwa").item(0).getTextContent();
			int productID = Integer.parseInt(eElement.getElementsByTagName("towar").item(0).getTextContent());
			int quantity = Integer.parseInt(eElement.getElementsByTagName("ilosc").item(0).getTextContent());
			double netUnitPrice = Double.parseDouble(eElement.getElementsByTagName("netto").item(0).getTextContent());
			double taxVatValue = Double.parseDouble(eElement.getElementsByTagName("vat").item(0).getTextContent());
			Product product = new Product(productName, productID, quantity, netUnitPrice, taxVatValue);
			productList.add(product);
		}

		return productList;
	}

}
