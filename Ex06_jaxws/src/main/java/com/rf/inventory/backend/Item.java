/**
 * 
 */
package com.rf.inventory.backend;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Data type corresponding to XML exchanged with the server. The XML looks like this:
 * <item productId="3212" quantity="4" />
 * 
 * @author v.lakshmanan
 *
 */

// TODO: Add annotation to indicate that "item" should be used as the root element of 
//       the exchanged XML
// HINT: See slide 6-19

public class Item {
    private int productId;
    private int quantity;

    public Item(){
        this(-1,-1);
    }
    public Item(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // TODO: Add annotation to indicate that productId is an attribute 
    //       of the item, not a child element
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    // TODO: Add annotation to indicate that quantity is an attribute of item, 
    // not a child element
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
