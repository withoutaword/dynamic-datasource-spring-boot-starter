package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能：多数据源配置属性
 *
 * @Author zhongshenghua
 * @Date 2018/6/25 15:12
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class MultiDataSourceProperties {

    /**
     * dao方法的包路径，用于配置aop动态切换数据源
     * 例子：com.zhongxiaohua.samples.dao.*
     */
    private List<String> patterns = new ArrayList<>();


}
