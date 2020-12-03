package com.zz.quartz.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zzj
 * @className TableFillHandler
 * @description TODO
 * @date 2020/1/13
 */

@Component
public class TableFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        if (metaObject.hasSetter("updateTime")) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
