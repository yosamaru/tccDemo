package me.yosamaru.tccdemo.enums;

public enum ProcessStatusEnum {

    PREPARATION(1, "准备中"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败");

    private Integer code;
    private String desc;

    /**
     * @param code * @param desc
     */
    ProcessStatusEnum(Integer code, String desc) {
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
