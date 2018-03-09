package database;

import itemfactory.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateItemList {

    private static Connection conn = null;
    public List<Item> itemList = new ArrayList<Item>();

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
            while (rs.next())
            {
                Item item = new Item();
                item.setItemName(rs.getString("Item Name"));
                item.setUnitPrice(rs.getDouble("Unit Price"));
                item.setWeightReqd(rs.getBoolean("Weight Reqd"));
                item.setQuanReqd(rs.getBoolean("Quan Reqd"));
                item.setRecalled(rs.getBoolean("Recall"));
                item.setAgeRest(rs.getBoolean("Age Rest"));
                item.seteCpn(rs.getBoolean("Ecpn"));
                item.seteCpnAmt(rs.getDouble("Ecpn Amt"));

                itemList.add(item);
                /*itemList.put(keyNum++, new Object[]
                        {
                                rs.getString("Item Name"),
                                rs.getDouble("Unit Price"),
                                rs.getBoolean("Weight Reqd"),
                                rs.getBoolean("Quan Reqd"),
                                rs.getBoolean("Recall"),
                                rs.getBoolean("Age Rest"),
                                rs.getBoolean("Ecpn"),
                                rs.getDouble("Ecpn Amt")});*/

            }

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e);
        }

    }
}
