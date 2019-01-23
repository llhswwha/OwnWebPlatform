package com.GetData.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GetData.domain.Tb_User;
import com.GetData.service.ITb_UserService;
import com.GetData.service.imp.T_UserServiceImp;
import com.GetData.unit.MD5Unit;
import com.GetData.unit.unitMysql;

/**
 * Servlet implementation class Servlet_TbUser
 */
@WebServlet("/Servlet_TbUser")
public class Servlet_TbUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//���ｨ�� ��service���  ��ϵ  ����һ��service��imp��ĳ���Ķ�  
	static ITb_UserService My_UserService=new T_UserServiceImp();
       
 

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
		String type=request.getParameter("who"); 
        if("Insert".equals(type)){
            Insert(request, response);
          }
           else if("Update".equals(type)){
        	   Update(request, response);
          } 
          else if("queryDataById".equals(type)){
        	  queryDataById(request, response);
          } 
           else if("Delete".equals(type)){
        	   Delete(request, response);
           } 
           else if("queryAll".equals(type)){
              queryAll(request, response);
         } 
           else if("DoLogin".equals(type))
           {
        	   DoLogin(request,response);
           }
           
	}
	
	//��ȡ��������
	public void queryAll(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		List<Tb_User> list=My_UserService.queryAllData(); 
		 request.setAttribute("userlist", list);
		 RequestDispatcher rd=request.getRequestDispatcher("/jsp/User.jsp");
         rd.forward(request, response);	
	}
	
	//��ѯһ�����ݸ��ݵ�¼��
	public void queryDataById(HttpServletRequest request,HttpServletResponse responce) throws ServletException,IOException
	{
		Tb_User user=null;
		String loginname=request.getParameter("loginName");
		if(loginname!=null&&loginname.length()>0)
		{
			user=My_UserService.quertDataByid(loginname);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/jsp/User.jsp").forward(request, responce);
		}
		
	}
	
	//�����û�
	public void Insert(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		      String loginname=request.getParameter("loginname");
		      String name=request.getParameter("name");
		      String pw=request.getParameter("pw");
		      String sex=request.getParameter("sex");
		  	  String birthday=request.getParameter("birthday");
		  	  String company=request.getParameter("company");
		  	  String dep=request.getParameter("dep");
		  	  String cpZJ=request.getParameter("cpZJ");
		  	  String homeZJ=request.getParameter("homeZJ");
		  	  String cpTel=request.getParameter("cpTel");
		  	  String tel=request.getParameter("tel");
		  	  String cpEmail=request.getParameter("cpEmail");
		  	  String email=request.getParameter("cpEmail");
		  	  String describe=request.getParameter("describe");
		  	  String validSityData=request.getParameter("validSityData");
		  	  String state=request.getParameter("state");
		  	  
		  	  Tb_User user=new Tb_User();
		  	  user.setloginName(loginname);
		  	  user.setname(name);
		  	  user.setpw(pw);
		  	  user.setsex(sex);
		  	  user.setbirthday(birthday);
		  	  user.setcompany(company);
		  	  user.setdep(dep);
		  	  user.setcpZJ(cpZJ);
		  	  user.sethomeZJ(homeZJ);
		  	  user.setcpTel(cpTel);
		  	  user.settel(tel);
		  	  user.setcpEmail(cpEmail);
		  	  user.setemail(email);
		  	  user.setdescribe(describe);
		  	  user.setvalidSityData(validSityData);
		  	  user.setstate(Integer.parseInt(state));
		  	  
		  	  //�����÷��������
              String message=null; 
               if (My_UserService.insertData(user)==true) { 
                   queryAll(request, response); 
              }
               else {
                   message="����ʧ��!!!";
                   request.setAttribute("msg", message);
                      //request.getRequestDispatcher("/index.jsp").forward(request, response); 
              }
		  	  
	}
	
	
	//�޸��û�
	public void Update(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		  String loginname=request.getParameter("loginname");
	      String name=request.getParameter("name");
	      String pw=request.getParameter("pw");
	      String sex=request.getParameter("sex");
	  	  String birthday=request.getParameter("birthday");
	  	  String company=request.getParameter("company");
	  	  String dep=request.getParameter("dep");
	  	  String cpZJ=request.getParameter("cpZJ");
	  	  String homeZJ=request.getParameter("homeZJ");
	  	  String cpTel=request.getParameter("cpTel");
	  	  String tel=request.getParameter("tel");
	  	  String cpEmail=request.getParameter("cpEmail");
	  	  String email=request.getParameter("cpEmail");
	  	  String describe=request.getParameter("describe");
	  	  String validSityData=request.getParameter("validSityData");
	  	  String state=request.getParameter("state");
	  	  
		  Tb_User user=new Tb_User();
	  	  user.setloginName(loginname);
	  	  user.setname(name);
	  	  user.setpw(pw);
	  	  user.setsex(sex);
	  	  user.setbirthday(birthday);
	  	  user.setcompany(company);
	  	  user.setdep(dep);
	  	  user.setcpZJ(cpZJ);
	  	  user.sethomeZJ(homeZJ);
	  	  user.setcpTel(cpTel);
	  	  user.settel(tel);
	  	  user.setcpEmail(cpEmail);
	  	  user.setemail(email);
	  	  user.setdescribe(describe);
	  	  user.setvalidSityData(validSityData);
	  	  user.setstate(Integer.parseInt(state));
	  	  
	  	boolean b=My_UserService.updateData(user);
	  	if (b==true) { 
            queryAll(request, response); 
       }
        else { 
           request.setAttribute("msg", "�޸�ʧ��!!");
               //request.getRequestDispatcher("/index.jsp").forward(request, response); 
      }
	  			
	  	  
	}
	
	//ɾ��
	public void Delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		 String loginname=request.getParameter("loginName");
		  boolean  message=My_UserService.deleteData(loginname); 
          if (message==true) { 
              queryAll(request, response); 
         }
          else {
              
              request.setAttribute("msg", "ɾ��ʧ��!!");
               //request.getRequestDispatcher("/index.jsp").forward(request, response); 
         }
	}

	
	//��¼��֤
	public static void DoLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		  String loginname=request.getParameter("loginName");
		  String password=MD5Unit.getMD5String(request.getParameter("pw"));
		
		  Boolean result=false;
		  String message="";
		  if(loginname==null||password==null)
		  {
			  result=false;
          	  message="";
			  //request.setAttribute("msg", "�û��������벻��Ϊ��");
		  }
		  else
		  {
		  Tb_User user=null;
			if(loginname!=null&&loginname.length()>0)
			{
				user=My_UserService.quertDataByid(loginname);
				  String  pws=user.pw.toString();
				if(pws.length()<=0||user.pw==null||user==null)//û����
		          {
		        	  result=false;
		          	  message="filed";
		        	  //request.setAttribute("msg", "�û�������");
		          }
		          else if(pws==password)
		          {
		        	  message="success";
		              result=true;
		        	  //��¼�ɹ���
		        	  request.getRequestDispatcher("/jsp/index.jsp").forward(request, response); 
		          }
		          else
		          {
		        	  result=false;
		          	  message="filed";
		        	  //request.setAttribute("msg", "��¼ʧ�ܣ�");
		          }
			}
          
          }
		  String msg="{'msg':'"+message+"','success':'"+result+"'}";
	        out.write(msg);
		  
	}



}
