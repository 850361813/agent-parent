package com.eden.agent.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

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
