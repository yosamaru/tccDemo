package me.yosamaru.tccdemo.export;

import me.yosamaru.tccdemo.bean.TccProcessInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TccProcessService {
    /**
     * 根据ID查询信息
     *
     * @param businessId
     * @return
     */
    TccProcessInfo selectByBusinessId(@Param("businessId") Long businessId);

    /**
     * 插入操作
     *
     * @param tccProcessInfo
     * @return
     */
    int insert(TccProcessInfo tccProcessInfo);

    /**
     * 更新操作
     *
     * @param tccProcessInfo
     * @return
     */
    int update(TccProcessInfo tccProcessInfo);

    /**
     * 更新超过一分钟的数据状态为失败
     *
     * @return
     */
    int updateDataForMoreThanOneMinute();

    /**
     * 查询try和cancel操作失败的数据
     *
     * @return
     */
    List<TccProcessInfo> queryTryAndCancelFailData();

    /**
     * 查询confirm操作失败的数据
     *
     * @return
     */
    List<TccProcessInfo> queryConfirmFailData();

    /**
     * 插入
     *
     * @param businessId
     * @param processType
     * @param processStatus
     */
    void insertProcessDB(Long businessId, Integer processType, Integer processStatus);

    /**
     * 修改
     *
     * @param businessId
     * @param processType
     * @param processStatus
     */
    void updateProcessDB(Long businessId, Integer processType, Integer processStatus);

}
