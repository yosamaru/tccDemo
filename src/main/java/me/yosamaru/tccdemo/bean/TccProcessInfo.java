package me.yosamaru.tccdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TccProcessInfo {
    private Long businessId;
    /**
     * 流程类型：1.try 2.confirm 3.cancel
     */
    private Integer processType;
    /**
     * 流程状态：1.准备中 2.成功 3.失败
     */
    private Integer processStatus;
}
