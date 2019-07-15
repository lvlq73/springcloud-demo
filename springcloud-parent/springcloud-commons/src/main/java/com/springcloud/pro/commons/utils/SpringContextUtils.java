package com.springcloud.pro.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 获取Spring中的Bean
     *
     * @param beanName
     *            Bean的名称
     * @return 如果成功则反回Bean对象，如果失败则抛出异常.
     */
    public static Object getBean(String beanName) throws Exception {
        if (applicationContext == null) {
            logger.warn("ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
            throw new Exception("ApplicationContext 没有初始化！您无法获得业务处理对象，系统无法正常运行");
        }
        try {
            return applicationContext.getBean(beanName);
        } catch (BeansException exp) {
            logger.error("读取[" + beanName + "]失败！", exp);
            throw new Exception("读取[" + beanName + "]失败！", exp);
        }
    }

    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
}
