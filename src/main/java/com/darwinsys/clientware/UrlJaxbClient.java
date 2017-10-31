package com.darwinsys.clientware;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

/**
 * A JAX-RS client NOT using the JAX-RS Client API
 */
public class UrlJaxbClient {

	static final String BASE_URL = 
			"http://androidcookbook.com/seam/resource/rest/recipe";

	public static void main(String[] args) throws Exception {
		URL url = new URL(BASE_URL + "/4");
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.addRequestProperty("Accept", MediaType.APPLICATION_JSON);
		InputStream is = connection.getInputStream();
		// Read "is" to get the response from a GET
		BufferedReader bi = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = bi.readLine()) != null) {
			System.out.println(line);
		}
	}
}
