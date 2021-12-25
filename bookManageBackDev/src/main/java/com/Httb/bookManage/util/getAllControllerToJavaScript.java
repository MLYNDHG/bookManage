package com.Httb.bookManage.util;

import com.Httb.bookManage.dao.SourceListExtDao;
import com.Httb.bookManage.mbg.entity.Sourcelist;
import com.alibaba.fastjson.JSON;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 获取本项目所有控制器
 */
/*
@Component
public class getAllControllerToJavaScript implements ApplicationContextAware, CommandLineRunner {

    @Resource
    private SourceListExtDao sourceListExtDao;

    @Value("${server.servlet.context-path}")
    private String path;//上下文路径获取
    private static ApplicationContext applicationContext;
    private static Map<String, Object> Itemcontrollers = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (getAllControllerToJavaScript.applicationContext == null) {
            getAllControllerToJavaScript.applicationContext = applicationContext;
        }
    }

    @Override
    public void run(String... args) {
        getController();
    }

    //封装控制器的  名称及控制器下方法的参数名称、类型，返回到拦截器进行匹配
    public void getController() {
        //获取使用RestController注解标注的的所有controller类
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);
        //遍历每个controller层
        for (Map.Entry<String, Object> entry : controllers.entrySet()) {
            Object value = entry.getValue();
//            System.out.println("拿到controller："+entry.getKey()+"，拿到value："+value);
            Class<?> aClass = AopUtils.getTargetClass(value);
//            System.out.println("拿到Class:" + aClass);
//            System.out.println("拿到controllerName:" +  aClass.getName().split("\\.")[aClass.getName().split("\\.").length-1]);

            String controllerName = aClass.getName().split("\\.")[aClass.getName().split("\\.").length - 1];
            // System.out.println(controllerName);//控制器名称
            // System.out.println(path);

            // 方法列表
            List<Method> methods = Arrays.asList(aClass.getDeclaredMethods());
//            System.out.println("Public Methods:" + methods);
            // 数据库中的方法列表
            List<Sourcelist> sourcelists = sourceListExtDao.selectSourceList();
            for (Method m: methods) {
                boolean tag = false;
                for (Sourcelist s: sourcelists) {
                    if (s.getName().equals(m.getName())) {
                        tag = true;
                        break;
                    }
                }
                if (!tag) {
                    Sourcelist sourcelist = new Sourcelist();
                    sourcelist.setName(m.getName());
                    sourcelist.setTime(new Date());
                    sourcelists.add(sourcelist);
                }

            }
            // 删除原来的源列表
            sourceListExtDao.deleteSourceList();
            // 插入新的源列表
            for (Sourcelist s: sourcelists) {
                sourceListExtDao.insertSourcelist(s);
            }
        }
    }
}*/
