package com.GetData.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GetData.service.ITb_CustomerService;
import com.GetData.service.imp.T_CustomerServiceImp;
import com.GetData.unit.MD5Unit;
import com.GetData.domain.Tb_Customer;
import com.GetData.domain.Tb_Message;

/**
 * Servlet implementation class Servlet_TbCustomer
 */
@WebServlet("/Servlet_TbCustomer")
public class Servlet_TbCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//这里建立 与service层的  联系  创建一个service层imp的某个的对  
	ITb_CustomerService My_CustomerService=new T_CustomerServiceImp();
    
	

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
		              else if("update".equals(type)){
		                  update(request, response);
		             } 
		             else if("queryById".equals(type)){
		                 queryById(request, response);
		             } 
		              else if("delete".equals(type)){
		                  delete(request, response);
		              } 
		              else if("queryAll".equals(type)){
		                 queryAll(request, response);
		            } 
		    
	}
	
	 //查询所有的数据
	     public void queryAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	             List<Tb_Customer> list=My_CustomerService.queryAllData(); 
	              request.setAttribute("list", list);
	                //  request.getRequestDispatcher("/jsp/Customer.jsp").forward(request, response);
	              RequestDispatcher rd=request.getRequestDispatcher("/jsp/Customer.jsp");
	               rd.forward(request, response);
	         }
	     //查一条
	     public void queryById(HttpServletRequest request, HttpServletResponse response)
	                      throws ServletException, IOException { 
	    	              Tb_Customer customer=null; 
	                    String  code=  request.getParameter("code");   
	                    System.out.println(code);
	                    customer=   My_CustomerService.quertDataByid(code); 
	                  request.setAttribute("customer", customer);
	                  request.getRequestDispatcher("/jsp/Customer.jsp").forward(request, response);
	           }
	     
	     
	     //新增
	     public void Insert(HttpServletRequest request, HttpServletResponse response)
	                      throws ServletException, IOException {
	               //这里jsp中name专递过来的参数
	    	      String code=request.getParameter("code");
	              String name=request.getParameter("name");
	              String pw=MD5Unit.getMD5String(request.getParameter("code"));
	              System.out.println(pw); 
	                //把获取到的这些值放到user里
	    	      Tb_Customer Customer =new Tb_Customer();

	    	      Customer.setCode(code);
	    	      Customer.setName(name);
	    	      Tb_Message message=new Tb_Message();
	    	    
	             
	               //最后调用服务来添加
	              //String message="成功！"; 
	              
	               if (My_CustomerService.insertData(Customer)==true) { 
	            	   message.setState(0);
	 	    	      message.setDescirbe("成功");
	                   request.setAttribute("msg", message);
	                   queryAll(request, response); 
	              }
	               else {
	                   //message="新增失败!!!";
	            	   message.setState(1);
	 	    	      message.setDescirbe("失败");
	                   request.setAttribute("msg", message);
	                   //request.getRequestDispatcher("/index.jsp").forward(request, response); 
	              }
	                
	          }  
	
          //修改
	     public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	     {
	    	 String code=request.getParameter("code");
	    	 String name=request.getParameter("name");
	    	 
	    	   Tb_Customer Customer =new Tb_Customer();
	    	   Customer.setCode(code);
	    	   Customer.setName(name);
	    	   
	    	   boolean b=  My_CustomerService.updateData(Customer);
	    	   if (b==true) { 
	    		                    queryAll(request, response); 
	    		               }
	    		                else { 
	    		                   request.setAttribute("msg", "修改失败!!");
	    		                       //request.getRequestDispatcher("/index.jsp").forward(request, response); 
	    		              }
	     }
	     
	     
	    //删除
	     public void delete(HttpServletRequest request, HttpServletResponse response)
	                      throws ServletException, IOException {  
	                  String  code= request.getParameter("code");   
	                   System.out.println(code);
	                   boolean  message=My_CustomerService.deleteData(code); 
	                   if (message==true) { 
	                       queryAll(request, response); 
	                  }
	                   else {
	                       
	                       request.setAttribute("msg", "删除失败!!");
	                          //request.getRequestDispatcher("/index.jsp").forward(request, response); 
	                  }
	           }
}
