package ro.management.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 10/25/2015.
 */
public enum AdminCategoryOrderSearch {
    DOMAIN("Domeniu"),
    SUBJECT("Subiect"),
    CLIENT("Client");

    private final String orderCategory;

    private AdminCategoryOrderSearch(String category) {
        orderCategory = category;
    }

    public String value() {
        return orderCategory;
    }

    public static List<String> valuesAsString() {
        List<String> values = new ArrayList<>();
        for (AdminCategoryOrderSearch val : AdminCategoryOrderSearch.values()) {
            values.add(val.orderCategory);
        }
        return values;
    }

    public static AdminCategoryOrderSearch getKey(String value) {
        for (AdminCategoryOrderSearch val : AdminCategoryOrderSearch.values()) {
            if (val.orderCategory.equals(value))
                return val;
        }
        return null;
    }
}
