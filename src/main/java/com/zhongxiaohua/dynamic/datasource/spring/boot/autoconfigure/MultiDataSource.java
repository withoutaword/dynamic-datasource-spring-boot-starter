package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能：主从数据源选择
 *
 * @Author zhongshenghua
 * @Date 2018/6/25 16:19
 */
@Slf4j
public class MultiDataSource implements DataSource {

    /**
     * 主库数据源
     */
    private DataSource masterDataSource;

    /**
     * 从库数据源
     */
    private DataSource slaveDataSource;

    /**
     * 获取数据源
     * @return
     */
    private DataSource getDataSource() {
        DataSource ds = null;
        if (DatasourceContext.getReadOnly()) {// 从库
            log.debug("动态切换数据源，当前为从库");
            ds = slaveDataSource;
        } else {
            log.debug("动态切换数据源，当前为主库");
            ds = masterDataSource;
        }
        return ds;
    }

    /**
     * 功能：设置主库数据源
     * 详细：
     * @param masterDataSource
     */
    public void setMasterDataSource(DataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    /**
     * 功能：设置从库数据源
     * 详细：
     * @param slaveDataSource
     */
    public void setSlaveDataSource(DataSource slaveDataSource) {
        this.slaveDataSource = slaveDataSource;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        getDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        getDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getDataSource().getLoginTimeout();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getDataSource().getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getDataSource().isWrapperFor(iface);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }

}
