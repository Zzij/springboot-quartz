package com.zz.quartz.job.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zzj
 * @className TestBean
 * @description TODO
 * @date 2020/12/2
 */

@Component
@Slf4j
public class TestBean {

    public void test(){
        log.info("start job");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end job");
    }
}
