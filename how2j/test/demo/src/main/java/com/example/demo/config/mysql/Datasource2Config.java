package com.example.demo.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "net.dgg.tac.data.warehouse.dao.ds2dao", sqlSessionFactoryRef = "sqlSessionFactory2")
public class Datasource2Config {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${database2.jdbc.url}")
    private String url;
    @Value("${database2.jdbc.username}")
    private String user;
    @Value("${database2.jdbc.password}")
    private String password;
    @Value("${database2.jdbc.driverClassName}")
    private String driverClass;
    @Value("${database2.jdbc.mapperLocation}")
    private String mapperLocation;

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        logger.info("dataSource2 url:{} user:{} password:{}", url, user, password);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        //连接池配置
        dataSource.setMaxActive(200);
        dataSource.setMinIdle(20);
        dataSource.setInitialSize(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dataSource2());
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource ds2DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds2DataSource);
        sessionFactory.setTypeAliasesPackage("net.dgg.tac.data.warehouse.common.mybatis.ds2.entity");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        return sessionFactory.getObject();
    }
}