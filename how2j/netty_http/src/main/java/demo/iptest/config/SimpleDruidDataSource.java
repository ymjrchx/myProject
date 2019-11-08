package demo.iptest.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author chenxin
 * @date 2019/4/8 13:27
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.iptest.mapper",sqlSessionTemplateRef = "ipRegionSqlSessionTemplate")
public class SimpleDruidDataSource {

    @ConfigurationProperties("spring.datasource.druid")
    @Bean(name="druidDataSource")
    @Primary
    public DataSource druidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name="ipRegionSessionFactory")
    @Primary
    public SqlSessionFactory ipRegionSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean  = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name="ipRegionTransactionManager")
    @Primary
    public DataSourceTransactionManager ipRegionTransactionManager(@Qualifier("druidDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="ipRegionSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate ipRegionSessionTemplate(@Qualifier("ipRegionSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }























    @Bean
    @ConditionalOnMissingBean

    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druidAdmin/*");
        servletRegistrationBean.addInitParameter("loginUsername","dgg");
        servletRegistrationBean.addInitParameter("loginPassword","dgg962540");
        servletRegistrationBean.addInitParameter("resetEnable","true");
        return servletRegistrationBean;

    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
