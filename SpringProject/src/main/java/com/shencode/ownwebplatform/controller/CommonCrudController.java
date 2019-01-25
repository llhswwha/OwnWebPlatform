package com.shencode.ownwebplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonCrudController {

    @RequestMapping("testCommonCrud")
    public String testCommonCrud(){
        return "testCommonCrud";
    }

    @RequestMapping("testOtherDao")
    public String testOtherDao(){
        return "testOtherDao";
    }

    @RequestMapping("testBootstrap")
    public String testBootstrap(){
        return "testBootstrap";
    }

    @RequestMapping("bootstrapTemplate")
    public String bootstrapTtemplate(){
        return "bootstrap_template";
    }

    @RequestMapping("bootstrapHello")
    public String bootstrapHello(){
        return "bootstrap_hello";
    }
}
