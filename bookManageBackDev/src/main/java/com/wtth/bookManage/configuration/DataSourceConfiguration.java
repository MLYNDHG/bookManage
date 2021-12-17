package com.wtth.bookManage.configuration;


import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

    @Resource
    private DataSource dataSource;
    /**
     * 分页拦截器
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        Properties properties = new Properties();
        // 方言
        properties.setProperty("helperDialect", "mysql");
        // 页码出界自动修正
        // pageNo < 1, 1
        // pageNo > totalPages, totalPages
        properties.setProperty("reasonable", "true");

        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);

        return pageInterceptor;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        // 初始化SqlSessionFactory
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 装载分页插件
//        sqlSessionFactoryBean.setPlugins(PageInterceptor());

        //此处设置为了解决找不到mapper文件的问题
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:mbg/**/*.xml");
        mapperLocations.add("classpath:mapper/**/*.xml");
        List<org.springframework.core.io.Resource> resources = new ArrayList<>();
        for (String mapperLocation : mapperLocations) {
            try {
                org.springframework.core.io.Resource[] mappers = resourceResolver.getResources(mapperLocation);
                resources.addAll(Arrays.asList(mappers));
            } catch (IOException e) {
                // ignore
            }
        }
        sqlSessionFactoryBean.setMapperLocations(resources.toArray(new org.springframework.core.io.Resource[resources.size()]));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
