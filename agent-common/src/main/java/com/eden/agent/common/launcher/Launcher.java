package com.eden.agent.common.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.eden.agent.common.executer.Executor;
import com.eden.agent.common.util.SpringHelper;

@Component("launcher")
public class Launcher {

    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private static String beanName;

    public static void main(String[] args) {

        logger.info("Launcher app start");

        Launcher launcher = (Launcher) SpringHelper.getApplicationContext().getBean("launcher");

        Assert.notNull(launcher, "Launcher is Null");

        if (args.length > 0) {
            switch (args.length) {
                case 1:
                    beanName = args[0];
                    break;
                default:
                    throw new RuntimeException("launcher param was not set right");
            }
        }

        Executor executor = (Executor) SpringHelper.getApplicationContext().getBean(beanName);
        executor.start();

    }
}
