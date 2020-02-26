package com.ydh.redsheep.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person implements BeanNameAware,BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean, BeanPostProcessor {

    private String name;

    public Person() {
        super();
        System.out.println("1初始化构造函数");
    }

    public String getName() {
        System.out.println("获取name 的属性值");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("2设置name 的属性值");
    }

    @PostConstruct
    public void springPostConstruct(){
        System.out.println("6@init");
    }

    @PreDestroy
    public void springPreDestory(){
        System.out.println("8@preDestory");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3setBeanName");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5setApplicationContext");
    }

    @Override
    public void destroy(){
        System.out.println("9destroy");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization"+ beanName);
        return bean;
    }
}
