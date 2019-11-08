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
@MapperScan(basePackages = "net.dgg.tac.data.warehouse.dao.ds4dao", sqlSessionFactoryRef = "sqlSessionFactory4")
public class Datasource4Config {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${database4.jdbc.url}")
    private String url;
    @Value("${database4.jdbc.username}")
    private String user;
    @Value("${database4.jdbc.password}")
    private String password;
    @Value("${database4.jdbc.driverClassName}")
    private String driverClass;
    @Value("${database4.jdbc.mapperLocation}")
    private String mapperLocation;
    @Bean(name = "dataSource4")
    public DataSource dataSource4() {
        logger.info("dataSource4 url:{} user:{} password:{}", url, user, password);
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
        dataSource.setMinEvictableIdleTimeMillis(400000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }

    @Bean(name = "transactionManager4")
    public DataSourceTransactionManager transactionManager4() {
        return new DataSourceTransactionManager(dataSource4());
    }

    @Bean(name = "sqlSessionFactory4")
    public SqlSessionFactory sqlSessionFactory4(@Qualifier("dataSource4") DataSource ds4DataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds4DataSource);
        //sessionFactory.setTypeAliasesPackage("net.dgg.tac.data.warehouse.common.mybatis.ds4.entity");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        return sessionFactory.getObject();
    }
}