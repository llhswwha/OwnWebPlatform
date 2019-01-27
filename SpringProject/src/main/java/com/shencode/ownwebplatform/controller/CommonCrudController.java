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
    public String bootstrapTemplate(){
        return "bootstrap_template";
    }

    @RequestMapping("bootstrapHello")
    public String bootstrapHello(){
        return "bootstrap_hello";
    }

    @RequestMapping("bootstrapTableTemplate")
    public String bootstrapTableTemplate(){return "bootstrap_table_template";}

    @RequestMapping("bootstrapTableTemplate2")
    public String bootstrapTableTemplate2(){return "bootstrap_table_template";}
}
