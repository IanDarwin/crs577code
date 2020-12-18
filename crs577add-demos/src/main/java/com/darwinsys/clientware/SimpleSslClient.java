package com.darwinsys.clientware;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

/**
 * Show that we can go to SSL without all the SSLConfiguration goo.
 * This will NOT work on the incredibly ancient Java used
 * in Course 577 as of 2020, since the CAcerts file is years out of date.
 */
public class SimpleSslClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		String url = Constants.ACB_BASE_URL + "/" + 1;
		System.out.println("Opening HTTPS URL " + url + " with NO SSL Config");
		Builder request = client.target(url).request();
		System.out.println("Invoking Builder Request " + request);
		
		Response resp = request.get();

		System.out.println("Response was: " + resp.getStatus());
	}
}
