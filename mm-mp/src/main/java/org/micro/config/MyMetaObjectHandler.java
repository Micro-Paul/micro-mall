package org.micro.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author micro-paul
 * @date 2021年12月22日 15:56
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入时自动填充
        // 注意第二个参数要填写实体类中的字段名称，而不是表的列名称
        try {
            if (metaObject.hasSetter("createTime")) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
            }
            if (metaObject.hasSetter("createDate")) {
                this.strictInsertFill(metaObject, "createDate", LocalDate.class, LocalDate.now());
            }
            if (metaObject.hasSetter("delFlag")) {
                this.strictInsertFill(metaObject, "delFlag", Boolean.class, Boolean.FALSE);
            }
        } catch (Exception e) {
            log.error("自动注入失败:{}", e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充
        // 注意第二个参数要填写实体类中的字段名称，而不是表的列名称
        try {
            if (metaObject.hasSetter("updateTime")) {
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
            if (metaObject.hasSetter("updateDate")) {
                this.strictInsertFill(metaObject, "updateDate", LocalDate.class, LocalDate.now());
            }
            if (metaObject.hasSetter("delFlag")) {
                this.strictInsertFill(metaObject, "delFlag", Object.class, null);
            }
        } catch (Exception e) {
            log.error("自动注入失败:{}", e);
        }
    }
}
