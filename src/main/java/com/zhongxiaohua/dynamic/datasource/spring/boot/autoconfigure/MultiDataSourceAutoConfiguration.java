package com.zhongxiaohua.dynamic.datasource.spring.boot.autoconfigure;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 功能：配置主从数据源
 *
 * @Author zhongshenghua
 * @Date 2018/6/25 15:13
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({MultiDataSourceProperties.class,MybatisProperties.class})
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MultiDataSourceAutoConfiguration {

    @Resource
    private DataSource dataSourceMaster;
    @Resource
    private DataSource dataSourceSlave;
    @Resource
    private MultiDataSource multiDataSource;
    @Autowired
    private MybatisProperties mybatisProperties;
    @Autowired
    private MultiDataSourceProperties properties;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource dataSourceMaster() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource dataSourceSlave() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 返回自定义的主从DataSource
     */
    @Bean
    public MultiDataSource multiDataSource() {
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setMasterDataSource(dataSourceMaster);
        multiDataSource.setSlaveDataSource(dataSourceSlave);
        return multiDataSource;
    }

    /**
     * 创建SqlSessionFactory，同时指定数据源
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置自定义的多数据源
        sqlSessionFactoryBean.setDataSource(multiDataSource);
        // 读取mybatis的配置填充回SqlSessionFactoryBean
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * datasource 事务
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new MultiDataSourceTransactionManager(multiDataSource);
    }

    /**
     * 拦截dao请求
     */
    @Bean
    public RegexpMethodPointcutAdvisor daoMethodPointcutAdvisor() {
        String[] patterns = properties.getPatterns().toArray(new String[properties.getPatterns().size()]);
        return new RegexpMethodPointcutAdvisor(patterns,new DaoMethodBeforeAdvice());
    }
}
