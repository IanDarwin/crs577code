package com.darwinsys.clientware;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;
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

	static final String BASE_URL = 
			"http://androidcookbook.com/seam/resource/rest/recipe";

	public static void main(String[] args) throws Exception {
		URL url = new URL(BASE_URL + "/4");
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.addRequestProperty("Accept", MediaType.APPLICATION_XML);
		InputStream is = connection.getInputStream();
		// Read "is" to get the response from a GET

		// Read into a DOM Tree and print to stdout
		Document tree = // DOM API
				DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		Transformer tx = TransformerFactory.newInstance().newTransformer();
		tx.setOutputProperty(OutputKeys.INDENT, "yes");
		tx.transform(new DOMSource(tree), new StreamResult(System.out));
	}
}
