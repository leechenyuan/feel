package test.me.leechanyuan.business.service;

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
}
