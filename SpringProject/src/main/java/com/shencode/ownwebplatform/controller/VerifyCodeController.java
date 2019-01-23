package com.shencode.ownwebplatform.controller;

import com.shencode.ownwebplatform.util.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class VerifyCodeController {

    @RequestMapping(value="/authImage",method= RequestMethod.GET)
    public String authImage(){
        return "authImage";
    }


    @RequestMapping(value="/getImage",method=RequestMethod.GET)
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }

    @RequestMapping(value="/validImage",method=RequestMethod.GET)
    @ResponseBody
    public String validImage(HttpServletRequest request){
        String code = request.getParameter("code");
        Object verCode = request.getSession().getAttribute("verCode");

        if (null == verCode) {
            return "验证码已失效，请重新输入";
        }
        String verCodeStr = verCode.toString();
        LocalDateTime localDateTime = (LocalDateTime)request.getSession().getAttribute("codeTime");
        long past = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if(verCodeStr == null || code == null || code.isEmpty() || !verCodeStr.equalsIgnoreCase(code)){
            return "验证码错误";
        } else if((now-past)/1000/60>5){
            return "验证码已过期，重新获取";
        } else {
            //验证成功，删除存储的验证码
            request.getSession().removeAttribute("verCode");
            return "success";
        }
    }





}
