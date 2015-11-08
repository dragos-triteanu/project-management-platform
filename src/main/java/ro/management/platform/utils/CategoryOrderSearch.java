package ro.management.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 10/19/2015.
 */
public enum CategoryOrderSearch {
    DOMAIN("Domeniu"),
    SUBJECT("Subiect");

    private final String orderCategory;
    private CategoryOrderSearch(String category) {
        orderCategory = category;
    }

    public String value(){
       return orderCategory;
    }

    public static List<String> valuesAsString() {
        List<String> values = new ArrayList<>();
        for (CategoryOrderSearch val : CategoryOrderSearch.values()) {
            values.add(val.orderCategory);
        }
        return values;
    }

    public static CategoryOrderSearch getKey(String value)
    {
        for (CategoryOrderSearch val : CategoryOrderSearch.values()) {
            if(val.orderCategory.equals(value))
                return val;
        }
        return null;
    }
}