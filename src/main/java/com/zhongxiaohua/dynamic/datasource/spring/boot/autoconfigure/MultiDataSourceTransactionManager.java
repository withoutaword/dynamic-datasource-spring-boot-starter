package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 功能：主从数据源的事务管理器
 * 详细：
 * @Author zhongshenghua
 * @Date 2018/6/25 16:25
 */
public class MultiDataSourceTransactionManager extends DataSourceTransactionManager {

    /**
     * 构造方法
     * @param dataSource
     */
    public MultiDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        if (definition.isReadOnly()) {
            DatasourceContext.setReadOnly(true);
        } else {
            DatasourceContext.setReadOnly(false);
        }
        super.doBegin(transaction, definition);
    }
}
