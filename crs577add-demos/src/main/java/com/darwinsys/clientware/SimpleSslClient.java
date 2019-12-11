package com.darwinsys.clientware;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * Show that we can go to SSL without all the SSLConfiguration goo.
 */
public class SimpleSslClient {

	public static void main(String[] args) {

		String baseUrl = "https://oracle.com/";
		System.out.println("Opening HTTPS URL " + baseUrl + " with NO SSL Config");
		Client client = ClientBuilder.newClient();

		Response resp = client.target(baseUrl).request().get();

		System.out.println("Response was: " + resp.getStatus());
	}
}
