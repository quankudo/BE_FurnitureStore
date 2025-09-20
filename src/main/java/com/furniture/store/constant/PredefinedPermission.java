package com.furniture.store.constant;

public class PredefinedPermission {

    // User permissions
    public static final String USER_READ   = "USER_READ";
    public static final String USER_CREATE = "USER_CREATE";
    public static final String USER_UPDATE = "USER_UPDATE";
    public static final String USER_DELETE = "USER_DELETE";

    // Product permissions
    public static final String PRODUCT_READ   = "PRODUCT_READ";
    public static final String PRODUCT_CREATE = "PRODUCT_CREATE";
    public static final String PRODUCT_UPDATE = "PRODUCT_UPDATE";
    public static final String PRODUCT_DELETE = "PRODUCT_DELETE";

    // Order permissions
    public static final String ORDER_READ   = "ORDER_READ";
    public static final String ORDER_CREATE = "ORDER_CREATE";
    public static final String ORDER_UPDATE = "ORDER_UPDATE";
    public static final String ORDER_DELETE = "ORDER_DELETE";

    private PredefinedPermission() {
        // prevent instantiation
    }
}
