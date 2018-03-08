package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CreateItemMap {

    private static Connection conn = null;
    public Map<Integer, Object[]> itemMap = new HashMap<Integer, Object[]>();

    public void createDbConnection() throws ClassNotFoundException, SQLException {
        String myUrl = "jdbc:mysql://localhost:3306/itemdb?autoReconnect=true&useSSL=false";
        conn =  DriverManager.getConnection(myUrl, "suprety", "Ktmsatch33");
    }

    public void addItemsToMap() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM item_source";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        try
        {
            int keyNum = 0;
            while (rs.next())
            {
                itemMap.put(keyNum++, new Object[]
                        {
                                rs.getString("Item Name"),
                                rs.getDouble("Unit Price"),
                                rs.getBoolean("Weight Reqd"),
                                rs.getBoolean("Quan Reqd"),
                                rs.getBoolean("Recall"),
                                rs.getBoolean("Age Rest"),
                                rs.getBoolean("Ecpn"),
                                rs.getDouble("Ecpn Amt")});

            }

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e);
        }
    }
}
