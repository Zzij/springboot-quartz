package com.zz.quartz.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zz.quartz.entity.ScheduleJobEntity;

/**
 * @author zzj
 * @className ScheduleJobServiceImpl
 * @description TODO
 * @date 2020/12/2
 */
public interface ScheduleJobService {

    Object queryPage(ScheduleJobEntity jobEntity);

    void insert(ScheduleJobEntity scheduleJob);

    void update(ScheduleJobEntity scheduleJob);

    void deleteBatch(Long[] jobIds);

    void run(Long[] jobIds);

    void pause(Long[] jobIds);

    void resume(Long[] jobIds);

    ScheduleJobEntity getById(long jobId);
}
