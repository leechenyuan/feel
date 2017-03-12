package test.me.feelwith.business.service;

import org.junit.Test;
import org.mybatis.generator.api.ShellRunner;

/**
 * Created by Administrator on 2016/8/2.
 */
public class GeneratorTest {
    @Test
    public void testGen(){
        String [] args = new String []{"-configfile"
                ,"e:\\tmp\\generatorConfig.xml"
                ,"-overwrite"};
        ShellRunner.main(args);
    }


    public static void main(String [] args){
        new GeneratorTest().testGen();
    }
}
