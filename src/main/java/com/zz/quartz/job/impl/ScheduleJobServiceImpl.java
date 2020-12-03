package com.zz.quartz.job.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.quartz.constant.CommonConstant;
import com.zz.quartz.entity.ScheduleJobEntity;
import com.zz.quartz.job.ScheduleJobService;
import com.zz.quartz.job.util.ScheduleUtil;
import com.zz.quartz.mapper.ScheduleJobMapper;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author zzj
 * @className ScheduleJobServiceImpl
 * @description TODO
 * @date 2020/12/2
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Override
    public IPage queryPage(ScheduleJobEntity jobEntity) {
        String beanName = jobEntity.getBeanName();
        QueryWrapper<ScheduleJobEntity> queryWrapper = new QueryWrapper<ScheduleJobEntity>();
        queryWrapper.like(StringUtils.isNotBlank(beanName), "job_id", beanName)
                .orderByDesc("job_id");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(1);
        // 设置页大小
        page.setSize(20);
        IPage<ScheduleJobEntity> iPage = scheduleJobMapper.selectPage(page, queryWrapper);
        return iPage;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ScheduleJobEntity scheduleJob) {
        scheduleJob.setStatus(CommonConstant.NORMAL);
        scheduleJobMapper.insert(scheduleJob);

        ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);

        scheduleJobMapper.updateById(scheduleJob);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        scheduleJobMapper.deleteBatchIds(Arrays.asList(jobIds));
    }


    public int updateBatch(Long[] jobIds, int status) {
        ScheduleJobEntity entity = new ScheduleJobEntity();
        entity.setJobId(jobIds[0]);
        entity.setStatus(status);
        return scheduleJobMapper.updateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.run(scheduler, scheduleJobMapper.selectById(jobId));
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, CommonConstant.PAUSE);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtil.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, CommonConstant.NORMAL);
    }

    @Override
    public ScheduleJobEntity getById(long jobId) {
        return scheduleJobMapper.selectById(jobId);
    }
}
