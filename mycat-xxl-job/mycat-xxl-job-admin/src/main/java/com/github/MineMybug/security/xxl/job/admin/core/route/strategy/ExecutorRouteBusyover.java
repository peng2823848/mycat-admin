package com.github.MineMybug.security.xxl.job.admin.core.route.strategy;

import java.util.List;

import com.github.MineMybug.security.xxl.job.admin.core.route.ExecutorRouter;
import com.github.MineMybug.security.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import com.github.MineMybug.security.xxl.job.admin.core.util.I18nUtil;
import com.github.MineMybug.security.xxl.job.core.biz.ExecutorBiz;
import com.github.MineMybug.security.xxl.job.core.biz.model.ReturnT;
import com.github.MineMybug.security.xxl.job.core.biz.model.TriggerParam;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteBusyover extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        StringBuffer idleBeatResultSB = new StringBuffer();
        for (String address : addressList) {
            // beat
            ReturnT<String> idleBeatResult = null;
            try {
                ExecutorBiz executorBiz = XxlJobDynamicScheduler.getExecutorBiz(address);
                idleBeatResult = executorBiz.idleBeat(triggerParam.getJobId());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                idleBeatResult = new ReturnT<String>(ReturnT.FAIL_CODE, ""+e );
            }
            idleBeatResultSB.append( (idleBeatResultSB.length()>0)?"<br><br>":"")
                    .append(I18nUtil.getString("jobconf_idleBeat") + "：")
                    .append("<br>address：").append(address)
                    .append("<br>code：").append(idleBeatResult.getCode())
                    .append("<br>msg：").append(idleBeatResult.getMsg());

            // beat success
            if (idleBeatResult.getCode() == ReturnT.SUCCESS_CODE) {
                idleBeatResult.setMsg(idleBeatResultSB.toString());
                idleBeatResult.setContent(address);
                return idleBeatResult;
            }
        }

        return new ReturnT<String>(ReturnT.FAIL_CODE, idleBeatResultSB.toString());
    }

}