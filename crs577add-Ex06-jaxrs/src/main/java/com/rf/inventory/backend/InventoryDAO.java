package com.rf.inventory.backend;

import java.util.List;

/**
 * Allows updates of inventory
 * Need not be thread-safe.
 * 
 * @author v.lakshmanan
 *
 */
public interface InventoryDAO {

    public void close();

    /** @return whether the row was updated */
    public boolean updateStockCount(int productId, int quantity);

    public boolean addItem(int productId, int quantity);
    
    public void removeItem(int productId);
    
    public List<Item> getItems();

	public Item getItem(int productId);
}
