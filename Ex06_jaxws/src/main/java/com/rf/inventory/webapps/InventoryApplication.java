package com.rf.inventory.webapps;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

// TODO: Examine the JAX-RS Application subclass. You'll need to add the
//       fully-qualified name of this class to web.xml.
//       (no code changes required in this file).

public class InventoryApplication extends Application {
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> sings = new HashSet<>();
		sings.add(new InventoryEndpointImpl());
		return sings;
	}
}
