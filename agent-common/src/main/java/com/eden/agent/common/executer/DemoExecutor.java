package com.eden.agent.common.executer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Create by zhaoxianghui on 2017/10/11.
 */
@Component("demo")
public class DemoExecutor implements Executor {

    @Value("${web.publish.number.perDay}")
    private String numberPerDay;

    @Override
    public void start() {
        System.out.println("hello world");
        System.out.println(numberPerDay);
    }
}
