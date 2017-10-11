package com.eden.agent.batch.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * Create by eden on 17/2/23.
 */
public class SpringHelper {

    private static final String DEFAULT_CONFIG_PATH = "classpath:/spring/spring-config.xml";

    private SpringHelper() {

    }

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            initApplicationContext();
        }
        return applicationContext;
    }

    private static void initApplicationContext() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(DEFAULT_CONFIG_PATH);
        if (!resource.exists()) {
            throw new IllegalArgumentException("Spring config path: " + DEFAULT_CONFIG_PATH + " does not exist!");
        } else {
            applicationContext = new GenericXmlApplicationContext(new Resource[] {resource});
        }
    }
}
