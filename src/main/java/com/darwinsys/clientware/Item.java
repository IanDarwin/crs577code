package com.darwinsys.clientware;

import javax.xml.bind.annotation.*;

/** Represents one Item in the warehouse database */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	int itemId;
	int stockCount;
}
