package com.story.storyadmin.constant.sysmgr;

public enum UserStatusEnum {

    NORMAL("1", "正常"),
    LOCK("0","禁用");

    private String code;
    private String val;

    UserStatusEnum(String code, String val) {
        this.code = code;
        this.val = val;
    }

    public String code() {
        return code;
    }

    public String val() {
        return val;
    }
}
