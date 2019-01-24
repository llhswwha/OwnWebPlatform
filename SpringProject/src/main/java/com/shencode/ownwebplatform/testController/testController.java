package com.shencode.ownwebplatform.testController;

import com.shencode.ownwebplatform.entity.City;
import com.shencode.ownwebplatform.module.condition.ui.ConditionModel;
import com.shencode.ownwebplatform.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class testController {
    @RequestMapping("test/hello")
    @ResponseBody
    public String sayhello()
    {
        return  "hello";
    }


@GetMapping("test/findCompany")
    @ResponseBody
    public List<City> users()
    {
        List<City> list=new LinkedList<City>();
        String pwname="123456";
        for (int i=0;i<2;i++)
        {
        City city =new City();
        city.setPid(1);
        city.setName(MD5Util.getMD5String(pwname));
        list.add(city);
        }
        return list;
    }

    @GetMapping("test/getList")
    public ModelAndView  getList()
    {
        ModelAndView mv=new ModelAndView("/test");
        mv.addObject("test","test");
        return  mv;
    }

    @RequestMapping("testConditionModel")//不能改成"test/conditionModel" 多个层级 js文件的引用就有问题
    public String testConditionModel(){
        return "testConditionModel";
    }

    @PostMapping("test/findPage")
    @ResponseBody
    public Object findPage(ConditionModel condition){
        System.out.println(condition);
        return condition;
    }

    @GetMapping("test/findPage2")
    @ResponseBody
    public Object findPage2(ConditionModel condition){
        System.out.println(condition);
        return condition;
    }
}
