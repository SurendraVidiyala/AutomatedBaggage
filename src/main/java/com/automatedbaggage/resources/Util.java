package com.automatedbaggage.resources;

import java.util.HashMap;
import java.util.Map;
public enum Util {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    A9("A9"),
    A10("A10"),
    CONCOURSE_A_TICKETING("Concourse_A_Ticketing"),
    BAGGAGE_CLAIM("BaggageClaim");

    public final String value;

    private Util(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, Util> UTIL_MAP = new HashMap<String, Util>();

    static {
        for (Util util : Util.values()) {
            UTIL_MAP.put(util.getValue(), util);
        }
    }

    public static Util getGate(String value) {
        return UTIL_MAP.get(value);
    }
}
