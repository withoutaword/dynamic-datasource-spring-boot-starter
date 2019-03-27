package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 功能：dao拦截器，设置查询操作使用从库
 *
 * @Author zhongshenghua
 * @Date 2018/6/25 15:13
 */
public class DaoMethodBeforeAdvice implements MethodBeforeAdvice {

    /**
     * 从库拦截的方法前缀
     */
    private static String[] methods = { "get", "count", "exist", "list", "select", "find" };

    @Override
    public void before(Method method, Object[] args, Object target) {
        // 默认查主库
        DatasourceContext.setReadOnly(false);
        // 如果拦截到方法前缀，查询从库
        for (String m : methods) {
            if (method.getName().startsWith(m)) {
                DatasourceContext.setReadOnly(true);
                break;
            }
        }
    }

}
