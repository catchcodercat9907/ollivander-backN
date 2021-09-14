package io.ollivander.ollivanderbackend.model.enums;

public enum AccountStatus {

    ACTIVE(0, "ACTIVE"),

    INACTIVE(1, "INACTIVE"),

    DEACTIVE(3, "DEACTIVE"),

    EXPIRED(4, "EXPIRED"),

    NEVER_LOGIN(5, "NEVER_LOGIN");

    private int code;
    private String name;

    private AccountStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static boolean isMember(int value) {
        for (AccountStatus item : AccountStatus.values()) {
            if (item.getCode() == value) {
                return true;
            }
        }
        return false;
    }
}