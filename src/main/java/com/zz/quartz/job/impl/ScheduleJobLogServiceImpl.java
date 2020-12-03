package com.zz.quartz.job.impl;

import com.zz.quartz.entity.ScheduleJobLogEntity;
import com.zz.quartz.job.ScheduleJobLogService;
import com.zz.quartz.mapper.ScheduleJobLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzj
 * @className ScheduleJobLogServiceImpl
 * @description TODO
 * @date 2020/12/2
 */

@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {


    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Override
    public void insert(ScheduleJobLogEntity jobLogEntity) {
        scheduleJobLogMapper.insert(jobLogEntity);
    }
}
