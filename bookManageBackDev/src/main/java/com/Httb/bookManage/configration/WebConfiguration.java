package com.Httb.bookManage.configration;

import com.Httb.bookManage.interceptor.DemoInterceptor;
import com.Httb.bookManage.interceptor.JWTInterceptor;
import com.Httb.bookManage.util.ConstantPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 跨站请求配置
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080", "http://localhost:8081")
                .allowedOriginPatterns("*") //允许跨域的域名，可以用*表示允许任何域名使用
                .allowedMethods("*")
//                .allowedMethods("POST", "GET", "OPTIONS", "DELETE", "PUT", "PATCH")
                .allowedHeaders("*")
//                .allowedHeaders("Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With", "uid", "platform", "userToken", "deviceInfo")
                .allowCredentials(true)
                .maxAge(3600);


        // add external api Mapping
//        registry.addMapping("/api/*")
//                .allowedOriginPatterns("http://10.1.1.1:8888")
//                .allowedMethods("POST", "GET", "OPTIONS", "DELETE", "PUT", "PATCH")
//                .allowedHeaders("Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With")
//                .allowCredentials(true)
//                .maxAge(3600);
        super.addCorsMappings(registry);
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger静态资源
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + ConstantPath.DIR);

        super.addResourceHandlers(registry);
    }

    @Resource
    private DemoInterceptor demoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * js拦截器
         */
        registry.addInterceptor(demoInterceptor);

        /**
         * JWT拦截器
         */
//        registry.addInterceptor(new JWTInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns(Collections.singletonList("/swagger-ui.html"))
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/doc.html")
//                .excludePathPatterns("/webjars/**");
    }
}
