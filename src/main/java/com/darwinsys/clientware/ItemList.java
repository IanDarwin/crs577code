package com.darwinsys.clientware;

import java.util.*;
import javax.xml.bind.annotation.*;

/** Trivial wrapper around a List<Item> */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemList {
	List<Item> items;
	
	List<Item> getItems() {
		return items;
	}
}
