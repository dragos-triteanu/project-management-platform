package eu.accesa.crowdfund.utils;

import java.util.UUID;

/**
 * Utility class for creating unique identifiers for persistence layer.
 * Created by Dragos on 9/16/2015.
 */
public class UUIDGenerator {
    public static final String DASH_PATTERN = "-";
    public static final String EMPTY_SPACE = "";

    /**
     * generates a random UUID using {@link java.util.UUID} and replaces all "-" with an empty char "".
     * @return
     */
    public static final String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll(DASH_PATTERN, EMPTY_SPACE);
    }
}
