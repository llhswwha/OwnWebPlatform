package com.shencode.ownwebplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public  String index(){
        return  "index";
    }
//    @RequestMapping("/out")
//    @ResponseBody
//    public Date out(Date sdate){
//        return sdate;
//    }

    @RequestMapping("/space.html")
    public String space(){
        return "space.html";
    }
    @RequestMapping("/system_management.html")
    public String system_management(){
        return "system_management.html";
    }
    @RequestMapping("/map.html")
    public String map(){
        return "map.html";
    }

    @RequestMapping("/device_warning.html")
    public String device_warning(){
        return "device_warning.html";
    }
    @RequestMapping("/device_management.html")
    public String device_management(){
        return "device_management.html";
    }
    @RequestMapping("/device.html")
    public String device(){
        return "device.html";
    }
    @RequestMapping("/customer.html")
    public String customer(){
        return "customer.html";
    }
    @RequestMapping("/centralized_monitoring.html")
    public String centralized_monitoring(){
        return "centralized_monitoring.html";
    }
}
