package com.darwinsys.clientware;

import java.util.List;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * A JAX-RS client NOT using the JAX-RS Client API
 */
public class UrlJaxbClient {

	static final String BASE_URL = "http://localhost:8080/inventory/rs/item";
	enum Mode {TREE, JAXB};
	static Mode mode = Mode.JAXB;

	public static void main(String[] args) throws Exception {
		URL url = new URL(BASE_URL + "/all");
		InputStream is = url.openConnection().getInputStream();
		// Read "is" to get the response from a GET

		// Either read into a Tree or into a JAXB
		if (mode == Mode.TREE) {
			Document tree = // DOM API
				DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			Transformer tx = TransformerFactory.newInstance().newTransformer();
			tx.setOutputProperty(OutputKeys.INDENT, "yes");
			tx.transform(new DOMSource(tree), new StreamResult(System.out));
		} else {
			JAXBContext jaxb = 
				JAXBContext.newInstance(ItemList.class, Item.class);
			ItemList itemList = 
				(ItemList) jaxb.createUnmarshaller().unmarshal(is);
			List<Item> list = itemList.getItems();
			System.out.println("Read " + list.size() + " items");
			for (Item i : list) {
				System.out.println(i);
			}
		}
	}
}
