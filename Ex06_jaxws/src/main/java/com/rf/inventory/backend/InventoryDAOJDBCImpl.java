package com.rf.inventory.backend;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Queries warehouse database
 * 
 * @author v.lakshmanan
 * 
 */
public class InventoryDAOJDBCImpl implements InventoryDAO {
    private static final String DELETE_SQL = "DELETE from STOCK where product_id = ?";
    private static final String QUERY_SQL = "SELECT product_id,quantity FROM STOCK WHERE product_id < 3050";
    private static final String INSERT_SQL = "INSERT into STOCK (quantity,product_id) VALUES (?,?) ";
    private static final String UPDATE_SQL = "UPDATE STOCK SET quantity=? where product_id = ?";
    private Logger log = LoggerFactory.getLogger(InventoryDAOJDBCImpl.class);
    private Connection conn; // non-pooled connection

    public InventoryDAOJDBCImpl() {
	String driver = null;
        try {
            Properties prop = new Properties();
            // InputStream is =
            InputStream is = this.getClass().getResourceAsStream(
                    "/database.properties");
            if (is == null) {
                throw new DataException("Unable to load database.properties from CLASSPATH.");
            }
            prop.load(is);
            String dbURL = prop.getProperty("DBURL");
            driver = prop.getProperty("DRIVER");
            String user = prop.getProperty("USER");
            String passwd = prop.getProperty("PASSWORD");
            Class.forName(driver);
            conn = DriverManager.getConnection(dbURL, user, passwd);
        } catch (ClassNotFoundException e) {
	    throw new DataException("Could not find driver on CLASSPATH: " + driver);
        } catch (Exception e) {
            throw new DataException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            // ok
        }
    }

    @Override
    public boolean updateStockCount(int productId, int quantity) {
        try {
            PreparedStatement updateStmt = conn.prepareStatement(UPDATE_SQL);
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, productId);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    // package friendly for unit testing
    Connection getConnection() {
        return conn;
    }

    @Override
    public boolean addItem(int productId, int quantity) {
        try {
            PreparedStatement insertStmt = conn.prepareStatement(INSERT_SQL);
            insertStmt.setInt(1, quantity);
            insertStmt.setInt(2, productId);
            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public List<Item> getItems() {
        try {
            PreparedStatement getStmt = conn.prepareStatement(QUERY_SQL);
            List<Item> items = new ArrayList<>();
            ResultSet rs = getStmt.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getInt(1), rs.getInt(2));
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public void removeItem(int productId) {
        try {
            PreparedStatement deleteStmt = conn.prepareStatement(DELETE_SQL);
            deleteStmt.setInt(1, productId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

	@Override
	public Item getItem(int productId) {
        try {
            PreparedStatement getStmt = conn.prepareStatement(
            		"SELECT product_id,quantity FROM STOCK WHERE product_id = ?");
            getStmt.setInt(1, productId);
            ResultSet rs = getStmt.executeQuery();
            if (rs.next()) {
                Item item = new Item(rs.getInt(1), rs.getInt(2));
                return item;
            }
            else throw new DataException("Item not found");
        } catch (SQLException e) {
            throw new DataException(e);
        }
	}
}
