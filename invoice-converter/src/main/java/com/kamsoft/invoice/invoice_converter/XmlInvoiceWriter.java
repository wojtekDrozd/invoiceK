package com.kamsoft.invoice.invoice_converter;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlInvoiceWriter extends InvoiceWriter {

	@Override
	public void writeFile(Invoice invoice) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("dokument");
			doc.appendChild(rootElement);

			// head element
			Element head = doc.createElement("naglowek");
			rootElement.appendChild(head);

			// date of invoice element
			Element dateOfInvoice = doc.createElement("data_w");
			dateOfInvoice.appendChild(doc.createTextNode(dateFormat.format(invoice.getHead().getDateOfInvoice())));
			head.appendChild(dateOfInvoice);

			// date of payment element
			Element dateOfPayment = doc.createElement("data_p");
			dateOfPayment.appendChild(doc.createTextNode(dateFormat.format(invoice.getHead().getDateOfPayment())));
			head.appendChild(dateOfPayment);

			// supplier ID element
			Element supplierID = doc.createElement("dostawca");
			supplierID.appendChild(doc.createTextNode(Integer.toString((invoice.getHead().getSupplierID()))));
			head.appendChild(supplierID);

			// receiver ID element
			Element receiverID = doc.createElement("odbiorca");
			receiverID.appendChild(doc.createTextNode(Integer.toString((invoice.getHead().getReceiverID()))));
			head.appendChild(receiverID);
			
			// iterate through positions in Invoice
			for (Position positionObj : invoice.getPositionsList()) {
				//product element
				Element product = doc.createElement("pozycja");
				rootElement.appendChild(product);
				
				// product name element
				Element productName = doc.createElement("nazwa");
				productName.appendChild(doc.createTextNode(positionObj.getProductName()));
				product.appendChild(productName);
				
				// productID element
				Element productID = doc.createElement("towar");
				productID.appendChild(doc.createTextNode(Long.toString(positionObj.getProductID())));
				product.appendChild(productID);
				
				// quantity element
				Element quantity = doc.createElement("ilosc");
				quantity.appendChild(doc.createTextNode(Integer.toString(positionObj.getQuantity())));
				product.appendChild(quantity);
				
				// net Unit Price element
				Element netUnitPrice = doc.createElement("netto");
				netUnitPrice.appendChild(doc.createTextNode(Double.toString(positionObj.getNetUnitPrice())));
				product.appendChild(netUnitPrice);
				
				// net Unit Price element
				Element taxVatValue = doc.createElement("vat");
				taxVatValue.appendChild(doc.createTextNode(Double.toString(positionObj.getTaxVatValue())));
				product.appendChild(taxVatValue);
			}
			

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(invoice.getInvoiceFileName()+".xml"));

			// Output to console for testing
			//result = new StreamResult(System.out);

			transformer.transform(source, result);

			//System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
