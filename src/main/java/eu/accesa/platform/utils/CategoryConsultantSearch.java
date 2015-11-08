package eu.accesa.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 10/21/2015.
 */
public enum CategoryConsultantSearch {
    NAME("Nume"),
    ADDRESS("Adresa");

    private final String consultantCategory;

    private CategoryConsultantSearch(String category) {
        consultantCategory = category;
    }

    public String value() {
        return consultantCategory;
    }

    public static List<String> valuesAsString() {
        List<String> values = new ArrayList<>();
        for (CategoryConsultantSearch val : CategoryConsultantSearch.values()) {
            values.add(val.value());
        }
        return values;
    }

    public static CategoryConsultantSearch getKey(String value) {
        for (CategoryConsultantSearch val : CategoryConsultantSearch.values()) {
            if (val.consultantCategory.equals(value))
                return val;
        }
        return null;
    }
}
