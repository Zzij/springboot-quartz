package com.zz.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zz.quartz.entity.ScheduleJobEntity;
import com.zz.quartz.job.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzj
 * @className QuartzConroller
 * @description TODO
 * @date 2020/12/2
 */
@Api(value = "定时任务接口", tags = {"定时任务接口"})
@RestController
@RequestMapping("/quartz")
public class QuartzConroller {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @ApiOperation(value = "定时任务列表", notes = "定时任务列表")
    @GetMapping("/list")
    public Object list() {

        return scheduleJobService.queryPage(new ScheduleJobEntity());
    }

    /**
     * 定时任务信息
     */
    @ApiOperation(value = "定时任务日志信息", notes = "定时任务日志信息")
    @ApiImplicitParam(paramType = "path", name = "jobId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{jobId}")
    public ScheduleJobEntity info(@PathVariable("jobId") Long jobId) {
        System.out.println(1111);
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);

        return schedule;
    }

    /**
     * 保存定时任务信息
     */
    @ApiOperation(value = "保存定时任务信息", notes = "保存定时任务信息")
    @PostMapping("/save")
    public void save(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.insert(scheduleJob);

        return;
    }

    /**
     * 修改定时任务信息
     */
    @ApiOperation(value = "修改定时任务信息", notes = "修改定时任务信息")
    @PostMapping("/update")
    public void update(@RequestBody ScheduleJobEntity scheduleJob) {

        scheduleJobService.update(scheduleJob);

        return;
    }

    /**
     * 删除定时任务信息
     */
    @ApiOperation(value = "删除定时任务信息", notes = "删除定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    public void delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return;
    }

    /**
     * 立即执行任务信息
     */
    @ApiOperation(value = "立即执行任务信息", notes = "立即执行任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/run")
    public void run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return;
    }

    /**
     * 暂停定时任务信息
     */
    @ApiOperation(value = "暂停定时任务信息", notes = "暂停定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/pause")
    public void pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return;
    }

    /**
     * 恢复定时任务信息
     */
    @ApiOperation(value = "恢复定时任务信息", notes = "恢复定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/resume")
    public void resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return;
    }
}
