package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

/**
 * 功能：数据库连接池上下文
 *
 * @Author zhongshenghua
 * @Date 2018/6/25 16:10
 */
public class DatasourceContext {

    private static final ThreadLocal<Boolean> readOnlyThreadLocal = new ThreadLocal<>();

    static {
        readOnlyThreadLocal.set(false);
    }

    /**
     * 功能：设置连接是否只读
     * @param readOnly true表示只读， false 非只读
     */
    public static void setReadOnly(boolean readOnly) {
        readOnlyThreadLocal.set(readOnly);
    }

    /**
     * 功能：获取当前连接是否只读
     * @return true 只读， false 非只读
     */
    public static boolean getReadOnly() {
        Boolean readOnly = readOnlyThreadLocal.get();
        if (readOnly != null) {
            return readOnly;
        } else {
            return true;
        }
    }

}
