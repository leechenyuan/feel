package test.me.leechanyuan.business.service;

import com.google.gson.Gson;
import me.leechenyuan.business.service.UserMapperService;
import me.leechenyuan.business.service.UserService;
import me.leechenyuan.common.bean.MyBeanUtil;
import org.junit.Test;

/**
 * Created by lideda on 2016/5/11.
 */
public class UserServiceTest {
    @Test
    public void transaction(){
        UserService service = MyBeanUtil.getBean("userService");
        service.incScore(false);
        service.incScore(true);
    }


    @Test
    public void testBatis() throws IllegalAccessException, InstantiationException {
        UserMapperService userMapperService = MyBeanUtil.getBean("userMapperService");
        System.out.println(Gson.class.newInstance().toJson(userMapperService.getUserById(1)));
    }

    @Test
    public void testBatisWithTransaction() throws IllegalAccessException, InstantiationException {
        UserMapperService userMapperService = MyBeanUtil.getBean("userMapperService");
        userMapperService.increaseScore(1,2,10,false);
        userMapperService.increaseScore(1,2,10,true);
        System.out.println(Gson.class.newInstance().toJson(userMapperService.getUserById(1)));
    }
}
