package com.rf.inventory.backend;

import java.util.List;
import java.sql.Connection;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Basic DAO tests
 * @author ROI
 */
public class InventoryDAOTest {
    protected InventoryDAOJDBCImpl dao;

	@BeforeClass
	public static void createTestDB() {
		System.out.println("InventoryDAOTest.createTestDB()");
		try {
			Connection c = new InventoryDAOJDBCImpl().getConnection();
			c.createStatement().executeUpdate("create table if not exists STOCK(product_id integer, quantity integer)");
			c.createStatement().executeUpdate("delete from STOCK");
			c.createStatement().executeUpdate("insert into STOCK(product_id, quantity) values(3012, 123)");
			c.close();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e.toString());
		}
	}
    
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
