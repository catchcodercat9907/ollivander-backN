package io.ollivander.ollivanderbackend.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ErrorInfo {
    public static final int UNKNOWN_ERROR_CODE = 1001;
    public static final int FIELD_ERROR_CODE = 1002;

    private static final Logger logger = LoggerFactory.getLogger(ErrorInfo.class);

    public static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream is = ErrorInfo.class.getResourceAsStream("/error_info.properties");
            properties.load(is);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private int code;
    private List<String> messages = new ArrayList<>();

    public static ErrorInfo newInstance(int code, String message) {
        return new ErrorInfo(code, message);
    }

    public ErrorInfo(int code, String... messages) {
        this.code = code;
        for (String message : messages) {
            this.messages.add(message);
        }
    }

    public ErrorInfo(String... messages) {
        this.code = UNKNOWN_ERROR_CODE;
        for (String message : messages) {
            this.messages.add(message);
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return messages.size() > 0 ? messages.get(0) : null;
    }

    public static final ErrorInfo INTERNAL_SERVER_ERROR = new ErrorInfo(UNKNOWN_ERROR_CODE,
            properties.getProperty("server.error"));

    public static final ErrorInfo PRODUCT_NOT_FOUND = new ErrorInfo(1,
            properties.getProperty("product.not.found"));

    public static final ErrorInfo ACCOUNT_NOT_FOUND = new ErrorInfo(2,
            properties.getProperty("account.not.found"));

    public static final ErrorInfo QUANTITY_NOT_VALID = new ErrorInfo(3,
            properties.getProperty("quantity.not.valid"));

    public static final ErrorInfo ACCESS_DENIED_ERROR = new ErrorInfo(4,
            properties.getProperty("access.denied.error"));

    public static final ErrorInfo FEATURE_NOT_FOUND = new ErrorInfo(5,
            properties.getProperty("feature.not.found"));

    /* Pls put code incrementing by 1 */

}
