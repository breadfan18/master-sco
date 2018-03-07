package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CreateItemMap {

    private static Connection conn = null;

    public void dbConnection() throws ClassNotFoundException, SQLException {
        //String myDriver = "com.mysql.jdbc.Driver";

        //?autoReconnect=true&useSSL=false
        //Class.forName(myDriver);

        String myUrl = "jdbc:mysql://localhost:3306/itemdb";
        conn =  DriverManager.getConnection(myUrl, "suprety", "Ktmsatch33");
    }

    public Map<Integer, Object[]> dbItemsMap() throws SQLException, ClassNotFoundException {
        Map<Integer, Object[]> itemMap = new HashMap<Integer, Object[]>();
        String query = "SELECT * FROM item_source";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        try
        {

            // iterate through the java resultset
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
        return itemMap;
    }
}
