package com.lxp.pro.lxpproweb.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * ClassName  : DatasourceConfig
 * Author     : Draghu
 * Date       : Create in 2019/12/5
 * Description: TODO
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig /*implements TransactionManagementConfigurer*/ {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    public DataSource datasource() {
        return DruidDataSourceBuilder.create().build();
    }
}