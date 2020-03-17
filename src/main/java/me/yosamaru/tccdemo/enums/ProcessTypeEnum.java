package me.yosamaru.tccdemo.enums;

public enum ProcessTypeEnum {

    TRY(1, "try"),
    CONFIRM(2, "confirm"),
    CANCEL(3, "cancel");

    private Integer code;
    private String desc;

    /**
     * @param code * @param desc
     */
    ProcessTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}