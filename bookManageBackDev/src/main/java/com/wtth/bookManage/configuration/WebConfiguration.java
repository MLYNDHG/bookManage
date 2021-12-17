package com.wtth.bookManage.configuration;

import com.wtth.bookManage.Interceptor.DemoInterceptor;
import com.wtth.bookManage.Interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8088/")
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

        super.addResourceHandlers(registry);
    }

    @Autowired
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
        registry.addInterceptor(new JWTInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns(Collections.singletonList("/swagger-ui.html"))
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**");
    }
}
