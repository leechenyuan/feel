package me.feelwith.common.bean;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lideda on 2016/5/10.
 */
public class MyBeanUtil {
    private static final ApplicationContext context = loadContext();
    @Deprecated
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        T bean = (T)context.getBean(name);
        return bean;
    }
    private static ApplicationContext loadContext(){
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("feels-servlet.xml");
//        return  context;
        return null;
    }
    public static Map toMap(Object ob){
        Map b = new BeanMap(ob);
        Iterator<Map.Entry> iter = b.entrySet().iterator();
        Map data = new HashMap<>();
        while(iter.hasNext()){
            Map.Entry entry = iter.next();
            data.put(entry.getKey(),entry.getValue());
        }
        return data;
    }
}
