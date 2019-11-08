package com.example.demo.iptest.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author chenxin
 * @date 2019/4/8 16:11
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.iptest.consultmessagemapper",sqlSessionTemplateRef = "messageSqlSessionTemplate")
public class ConsultMessageDruidDataSource {

    @Bean(name = "messageDateSource")
    @ConfigurationProperties(prefix = "spring.mydatesource.druid1")
    public DataSource messageDateSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name="messageSqlSessionFactory")
    public SqlSessionFactory messageSqlSessionFactory(@Qualifier("messageDateSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/consultMessageMapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name="messageTransactionManager")
    public DataSourceTransactionManager messageTransactionManager(@Qualifier("messageDateSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="messageSqlSessionTemplate")
    public SqlSessionTemplate messageSqlSessionTemplate(@Qualifier("messageSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
































}
