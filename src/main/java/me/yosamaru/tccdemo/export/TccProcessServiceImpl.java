package me.yosamaru.tccdemo.export;

import me.yosamaru.tccdemo.bean.TccProcessInfo;
import me.yosamaru.tccdemo.dao.TccProcessMapper;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import javax.annotation.Resource;
import java.util.List;

public class TccProcessServiceImpl implements TccProcessService {
    @Resource
    private TccProcessMapper tccProcessMapper;
    @Override
    public TccProcessInfo selectByBusinessId(Long businessId) {
        return tccProcessMapper.selectBybusinessId(businessId);
    }

    @Override
    public int insert(TccProcessInfo tccProcessInfo) {
      return tccProcessMapper.insert(tccProcessInfo);
    }

    @Override
    public int update(TccProcessInfo tccProcessInfo) {
        return tccProcessMapper.update(tccProcessInfo);
    }

    @Override
    public int updateDataForMoreThanOneMinute() {
        return tccProcessMapper.updateDataForMoreThanOneMinute();
    }

    @Override
    public List<TccProcessInfo> queryTryAndCancelFailData() {
        return tccProcessMapper.queryTryAndCancelFailData();
    }

    @Override
    public List<TccProcessInfo> queryConfirmFailData() {
        return tccProcessMapper.queryConfirmFailData();
    }

    public void insertProcessDB(Long businessId, Integer processType, Integer processStatus) {
        //try操作 准备中
        TccProcessInfo tccProcessInfo = new TccProcessInfo(businessId, processType, processStatus);
        this.insert(tccProcessInfo);
    }

    public void updateProcessDB(Long businessId, Integer processType, Integer processStatus){
        //try操作 准备中
        TccProcessInfo tccProcessInfo = new TccProcessInfo(businessId, processType, processStatus);
        this.update(tccProcessInfo);
    }
}
