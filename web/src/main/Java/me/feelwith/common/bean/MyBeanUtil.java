package me.feelwith.common.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lideda on 2016/5/10.
 */
public class MyBeanUtil {
    private static final ApplicationContext context = loadContext();
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        T bean = (T)context.getBean(name);
        return bean;
    }
    private static ApplicationContext loadContext(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("feels-servlet.xml");
        return  context;
    }
}
