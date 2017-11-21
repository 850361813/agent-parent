package com.eden.agent.ws;

import org.springframework.stereotype.Component;

import com.eden.agent.common.executer.Executor;

/**
 * Create by zhaoxianghui on 2017/11/22.
 */
@Component("ebay")
public class EbayFetchTask implements Executor {
    @Override
    public void start() {
        System.out.println("to be impl");
    }
}
