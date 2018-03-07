package database;

import java.sql.SQLException;
import java.util.Map;

public class test {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateItemMap im = new CreateItemMap();

        im.dbConnection();

        String format = "%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-12s";
        for (Integer key: im.dbItemsMap().keySet()) {
            Object[] value = im.dbItemsMap().get(key);
            System.out.format(format, value[0], value[1], value[2], value[3], value[4], value[5], value[6], value[7]);
            System.out.println();
        }
    }
}
