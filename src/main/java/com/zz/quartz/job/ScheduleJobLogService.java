package com.zz.quartz.job;

import com.zz.quartz.entity.ScheduleJobLogEntity;

/**
 *@author: zzj
 *@date: 2020/12/2
 *
**/
public interface ScheduleJobLogService {

    void insert(ScheduleJobLogEntity jobLogEntity);
}
