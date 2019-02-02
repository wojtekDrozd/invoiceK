package com.kamsoft.invoice.invoice_converter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReadTest {

	private static NodeList createNodeList(File file, String nodeName)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(nodeName);

		return nList;

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("Faktura1.xml");
		NodeList nList = createNodeList(file, "naglowek");
		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		System.out.println(eElement.getElementsByTagName("odbiorca").item(0).getTextContent());
		System.out.println(eElement.getElementsByTagName("dostawca").item(0).getTextContent());
		System.out.println(eElement.getElementsByTagName("data_p").item(0).getTextContent());
		System.out.println(parseDate(eElement.getElementsByTagName("data_w").item(0).getTextContent()));

	}

	// parse date formatted in given way
	public static Date parseDate(String stringDate) {
		Date dateOfInvoice = null;
		try {
			dateOfInvoice = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
		} catch (ParseException e) {
			System.out.println("Incorrect date format");
			e.printStackTrace();
		}
		return dateOfInvoice;
	}

}
