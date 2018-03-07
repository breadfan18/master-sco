package database;

import utils.BasePage;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ItemProcessor {
    BasePage bp = new BasePage();

    public boolean itemProcessor(Map<Integer, Object[]> itemMap, String item){
        for (Integer key: itemMap.keySet()) {
            Object[] value = itemMap.get(key);
            if (value[0].toString().equalsIgnoreCase(item)){
                return true;
            }
        }
        return false;
    }

}
