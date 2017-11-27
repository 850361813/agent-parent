package com.eden.agent.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: changjunjie01
 * Date: 2017/5/26
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/test-spring-config.xml"})
public class BaseTest {

    @Test
    public void test() {
        System.out.println("nothing");
    }

}
