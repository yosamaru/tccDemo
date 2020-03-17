package me.yosamaru.tccdemo.handler;

import me.yosamaru.tccdemo.bean.TccProcessInfo;
import me.yosamaru.tccdemo.enums.ProcessStatusEnum;
import me.yosamaru.tccdemo.enums.ProcessTypeEnum;
import me.yosamaru.tccdemo.export.TccProcessService;

import javax.annotation.Resource;

/**
 * 利用TCC三阶段提交进行余额支付
 */
public abstract class AbstractPayBalanceProcessHandler {
    @Resource
    private TccProcessService tccProcessService;

    /**
     * 冻结操作，属于TCC三阶段中的TRY
     *
     * @param businessId
     * @param processType
     * @param processStatus
     */
    public void freezeProcess(Long businessId, Integer processType, Integer processStatus) {
        TccProcessInfo tccProcessInfo = tccProcessService.selectByBusinessId(businessId);
        if (tccProcessInfo == null) {
            tccProcessService.insertProcessDB(businessId, processType, processStatus);
        }

        try {
            this.freezeHandler();
            //process为TRY，Status：为SUCCESS
            tccProcessService.updateProcessDB(businessId, processType, processStatus);
        } catch (Exception e) {
            //process为TRY，Status：为FAIL
            tccProcessService.updateProcessDB(businessId, processType, processStatus);
        }
    }


    /**
     * 扣减金额，TCC三阶段中的Confirm
     *
     * @param businessId
     * @param processType
     * @param processStatus
     */
    public void deductProcess(Long businessId, Integer processType, Integer processStatus) {
        TccProcessInfo tccProcessInfo = tccProcessService.selectByBusinessId(businessId);
        if (ProcessTypeEnum.TRY.getCode().equals(tccProcessInfo.getProcessType()) &&
                ProcessStatusEnum.SUCCESS.getCode().equals(tccProcessInfo.getProcessStatus())) {

            try {
                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CONFIRM.getCode(), ProcessStatusEnum.PREPARATION.getCode());

                this.deductHandler();

                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CONFIRM.getCode(), ProcessStatusEnum.SUCCESS.getCode());


            } catch (Exception e) {
                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CONFIRM.getCode(), ProcessStatusEnum.FAIL.getCode());
            }
        }

    }

    /**
     * 解冻退款操作，TCC三阶段中的Cancel
     *
     * @param businessId
     * @param processType
     * @param processStatus
     */
    public void thawProcess(Long businessId, Integer processType, Integer processStatus) {
        TccProcessInfo tccProcessInfo = tccProcessService.selectByBusinessId(businessId);
        if (ProcessTypeEnum.TRY.getCode().equals(tccProcessInfo.getProcessType()) &&
                ProcessStatusEnum.FAIL.getCode().equals(tccProcessInfo.getProcessStatus())) {
            try {
                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CANCEL.getCode(), ProcessStatusEnum.PREPARATION.getCode());
                this.thawHandler();
                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CANCEL.getCode(), ProcessStatusEnum.SUCCESS.getCode());
            } catch (Exception e) {
                tccProcessService.updateProcessDB(businessId, ProcessTypeEnum.CANCEL.getCode(), ProcessStatusEnum.FAIL.getCode());

            }
        }
    }

    /**
     * 冻结，具体的业务处理
     */
    public abstract void freezeHandler();

    /**
     * 扣减，具体的业务处理
     */
    public abstract void deductHandler();

    /**
     * 解冻，具体的业务处理
     */
    public abstract void thawHandler();

}
