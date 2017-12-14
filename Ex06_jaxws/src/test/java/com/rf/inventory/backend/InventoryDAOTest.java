package com.rf.inventory.backend;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Basic DAO tests
 * @author ROI
 */
public class InventoryDAOTest {
    protected InventoryDAOJDBCImpl dao;
    
    @Before
    public void setUp() throws Exception {
        dao = new InventoryDAOJDBCImpl();
        dao.getConnection().setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        dao.getConnection().rollback();
        dao.close();
    }

    @Test
    public void testUpdate(){
        boolean updated = dao.updateStockCount(3012, 5);
        assertTrue(updated);
        List<Item> itemList = dao.getItems();
        for (Item item : itemList){
            if (item.getProductId() == 3012){
                assertTrue(item.getQuantity() == 5);
                return;
            }
        }
        fail("Could not find 3012");
    }
    
    @Test
    public void testGetAll(){
        List<Item> itemList = dao.getItems();
        assertTrue(itemList.size() > 0);
    }
    
    @Test
    public void testDelete(){
        dao.removeItem(3012);
        List<Item> itemList = dao.getItems();
        for (Item item : itemList){
            if (item.getProductId() == 3012){
                fail("Item not removed");
            }
        }
    }
    
    @Test
    public void testInsert(){
        dao.addItem(12, 3);
        List<Item> itemList = dao.getItems();
        for (Item item : itemList){
            if (item.getProductId() == 12){
                assertTrue(item.getQuantity() == 3);
                return; // ok
            }
        }
        fail("Item not inserted");
    }
}
