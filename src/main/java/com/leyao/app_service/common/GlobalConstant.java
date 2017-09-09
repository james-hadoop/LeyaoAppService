package com.leyao.app_service.common;

public class GlobalConstant {
    public static final String NULL_STRING = "?";
    
    public static final String SESSION_CODE_PREFIX="SN";

    private static String EVENT_VERSION = "";

    public static String getEVENT_VERSION() {
        return EVENT_VERSION;
    }

    public static void setEVENT_VERSION(String eVENT_VERSION) {
        EVENT_VERSION = eVENT_VERSION;
    }
}