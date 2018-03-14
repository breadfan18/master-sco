package database;

import itemfactory.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateItemList {

    private static Connection conn = null;
    public List<Item> itemList = new ArrayList<Item>();
    public Map<Integer, Item> testMap = new HashMap<Integer, Item>();

    public void createDbConnection() {
        String myUrl = "jdbc:mysql://localhost:3306/itemdb?autoReconnect=true&useSSL=false";
        try {
            conn =  DriverManager.getConnection(myUrl, "suprety", "Ktmsatch33");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemsToList()  {
        int keyNum = 0;
        String query = "SELECT * FROM item_source";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


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

                //itemList.add(item);
                testMap.put(keyNum++, item);
            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e);
        }
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
