package com.GetData.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import jdk.nashorn.api.scripting.JSObject;

import com.GetData.domain.Tb_Message;

import sun.awt.RepaintArea;

/**
 * Servlet implementation class checkCode
 */
@WebServlet("/checkCode")
public class checkCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
		String code=request.getParameter("code");
		Boolean result=false;
	   String message="";
		 // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
           if (code.equalsIgnoreCase(sessionCode)) {
               message="success";
               result=true;
               // request.setAttribute("msg", message);
           } else {
               result=false;
            	message="filed";
               // request.setAttribute("msg", message);
            }
        } else 
        {
        	result=false;
        	message="验证失败！";
           // request.setAttribute("msg",message);
       }
        String msg="{'msg':'"+message+"','success':'"+result+"'}";
        out.write(msg);
       
	}

}
