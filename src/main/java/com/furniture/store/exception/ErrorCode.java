package com.furniture.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1009, "Role already existed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1010, "Role not existed", HttpStatus.NOT_FOUND),
    PERMISSION_EXISTED(1011, "Permission already existed", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTED(1012, "Permission not existed", HttpStatus.NOT_FOUND),
    TOKEN_NOT_FOUND(1013, "Activation token not found", HttpStatus.BAD_REQUEST),
    CATEGORY_ALREADY_EXISTS(1014, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1015, "Category not existed", HttpStatus.NOT_FOUND),
    INVALID_OLD_PASSWORD(1016, "Old password invalid", HttpStatus.BAD_REQUEST),
    CITY_ALREADY_EXISTS(1017, "City already existed", HttpStatus.BAD_REQUEST),
    CITY_NOT_FOUND(1018, "City not existed", HttpStatus.NOT_FOUND),
    DISTRICT_ALREADY_EXISTS(1019, "District already existed", HttpStatus.BAD_REQUEST),
    DISTRICT_NOT_FOUND(1020, "District not existed", HttpStatus.NOT_FOUND),
    ADDRESS_ALREADY_EXISTS(1021, "Address already existed",HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(1021, "Address not existed",HttpStatus.NOT_FOUND),
    COLOR_ALREADY_EXISTS(1022, "Color already existed", HttpStatus.BAD_REQUEST),
    MATERIAL_ALREADY_EXISTS(1023, "Material already existed", HttpStatus.BAD_REQUEST);
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
