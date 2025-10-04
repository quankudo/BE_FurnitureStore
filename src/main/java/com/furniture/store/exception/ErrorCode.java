package com.furniture.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // ====== Common ======
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),

    // ====== User ======
    USER_EXISTED(2001, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(2002, "User not existed", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(2003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(2004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_DOB(2005, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // ====== Auth ======
    UNAUTHENTICATED(2101, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(2102, "You do not have permission", HttpStatus.FORBIDDEN),
    TOKEN_NOT_FOUND(2103, "Activation token not found", HttpStatus.BAD_REQUEST),

    // ====== Role ======
    ROLE_EXISTED(2201, "Role already existed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(2202, "Role not existed", HttpStatus.NOT_FOUND),

    // ====== Permission ======
    PERMISSION_EXISTED(2301, "Permission already existed", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTED(2302, "Permission not existed", HttpStatus.NOT_FOUND),

    // ====== Category ======
    CATEGORY_ALREADY_EXISTS(3001, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(3002, "Category not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_REQUIRED(3003, "Tên danh mục không được để trống", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_TOO_LONG(3004, "Tên danh mục không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),
    CATEGORY_DESC_TOO_LONG(3005, "Mô tả không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),

    // ====== City ======
    CITY_ALREADY_EXISTS(4001, "City already existed", HttpStatus.BAD_REQUEST),
    CITY_NOT_FOUND(4002, "City not existed", HttpStatus.NOT_FOUND),
    CITY_NAME_REQUIRED(4003, "Tên thành phố không được để trống", HttpStatus.BAD_REQUEST),
    CITY_NAME_TOO_LONG(4004, "Tên thành phố không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),

    // ====== District ======
    DISTRICT_ALREADY_EXISTS(4101, "District already existed", HttpStatus.BAD_REQUEST),
    DISTRICT_NOT_FOUND(4102, "District not existed", HttpStatus.NOT_FOUND),

    // ====== Address ======
    ADDRESS_ALREADY_EXISTS(5001, "Address already existed", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(5002, "Address not existed", HttpStatus.NOT_FOUND),
    ADDRESS_DETAIL_REQUIRED(5003, "Chi tiết địa chỉ không được để trống", HttpStatus.BAD_REQUEST),
    ADDRESS_DETAIL_TOO_LONG(5004, "Chi tiết địa chỉ không được vượt quá 255 ký tự", HttpStatus.BAD_REQUEST),
    ADDRESS_NAME_REQUIRED(5005, "Tên người nhận không được để trống", HttpStatus.BAD_REQUEST),
    ADDRESS_NAME_TOO_LONG(5006, "Tên người nhận không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),
    ADDRESS_PHONE_REQUIRED(5007, "Số điện thoại không được để trống", HttpStatus.BAD_REQUEST),
    ADDRESS_PHONE_INVALID(5008, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),

    // ====== Color ======
    COLOR_ALREADY_EXISTS(6001, "Color already existed", HttpStatus.BAD_REQUEST),
    COLOR_NOT_FOUND(6002, "Color not existed", HttpStatus.NOT_FOUND),

    // ====== Material ======
    MATERIAL_ALREADY_EXISTS(7001, "Material already existed", HttpStatus.BAD_REQUEST),
    MATERIAL_NOT_FOUND(7002, "Material not existed", HttpStatus.NOT_FOUND),
    MATERIAL_NAME_REQUIRED(7003, "Tên chất liệu không được để trống", HttpStatus.BAD_REQUEST),
    MATERIAL_NAME_TOO_LONG(7004, "Tên chất liệu không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),
    MATERIAL_DESC_TOO_LONG(7005, "Mô tả không được vượt quá 255 ký tự", HttpStatus.BAD_REQUEST),

    // ====== Supplier ======
    SUPPLIER_ALREADY_EXISTS(8001, "Supplier already existed", HttpStatus.BAD_REQUEST),
    SUPPLIER_NOT_FOUND(8002, "Supplier not existed", HttpStatus.NOT_FOUND),

    // ====== Product ======
    PRODUCT_ALREADY_EXISTS(9001, "Product already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(9002, "Product not existed", HttpStatus.NOT_FOUND),

    // ====== Blog Category ======
    BLOG_CATEGORY_ALREADY_EXISTS(9001, "Blog category already existed", HttpStatus.BAD_REQUEST),
    BLOG_CATEGORY_NOT_FOUND(9002, "Blog category not existed", HttpStatus.NOT_FOUND),

    // ====== Tag Category ======
    TAG_ALREADY_EXISTS(9001, "Tag already existed", HttpStatus.BAD_REQUEST),
    TAG_NOT_FOUND(9002, "Tag not existed", HttpStatus.NOT_FOUND),

    // ====== Tag Category ======
    BLOG_ALREADY_EXISTS(9001, "Blog already existed", HttpStatus.BAD_REQUEST),
    BLOG_NOT_FOUND(9002, "Blog not existed", HttpStatus.NOT_FOUND),

    // ====== Comment ======
    COMMENT_ALREADY_EXISTS(9001, "Comment already existed", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND(9002, "Comment not existed", HttpStatus.NOT_FOUND),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
