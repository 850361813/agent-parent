package com.eden.agent.web.controller;

import com.eden.agent.domain.User;
import com.eden.agent.service.UserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/get")
    public List<User> demoGet() {
        List<User> list = userService.selectUser();
        for (User user : list) {
            System.out.println(user.getName());
        }
        return list;
    }

    @RequestMapping(value="list")
    public String list(){
        return "list";
    }

    @RequestMapping(value="hello")
    public String helloFtl(ModelMap model){
        model.put("time", new Date());
        model.put("message","eden");
        return "hello";
    }
}
