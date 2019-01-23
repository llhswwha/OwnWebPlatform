package com.GetData.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GetData.domain.Tb_Compary;
import com.GetData.domain.Tb_Message;
import com.GetData.domain.Tb_SpaceRes;
import com.GetData.service.ITb_SpaceResService;
import com.GetData.service.imp.T_SpaceResServiceImp;
import com.GetData.unit.GuidUnit;

/**
 * Servlet implementation class Servlet_TbSpaceRes
 */
@WebServlet("/Servlet_TbSpaceRes")
public class Servlet_TbSpaceRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//这里建立 与service层的  联系  创建一个service层imp的某个的对   
	ITb_SpaceResService  My_SpaceResService=new T_SpaceResServiceImp();

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
            List<Tb_SpaceRes> list=My_SpaceResService.queryAllData(); 
             request.setAttribute("list", list);
               //  request.getRequestDispatcher("/jsp/Customer.jsp").forward(request, response);
             RequestDispatcher rd=request.getRequestDispatcher("/jsp/.jsp");
              rd.forward(request, response);
        }
    
  //查一条
    public void queryById(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException { 
    	       Tb_SpaceRes SpaceRes=null; 
                   String  ID=  request.getParameter("ID");   
                   System.out.println(ID);
                   SpaceRes=   My_SpaceResService.quertDataByid(ID); 
                 request.setAttribute("SpaceRes", SpaceRes);
                 request.getRequestDispatcher("/jsp/.jsp").forward(request, response);
          }
    
  //新增
    public void Insert(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
              //这里jsp中name专递过来的参数
    	     String id=GuidUnit.getGuid();
             String name=request.getParameter("name");
             String area=request.getParameter("area");
             String comparyID=request.getParameter("comparyID");
             String code=request.getParameter("code");
             String address=request.getParameter("address");
             String charge=request.getParameter("charge");
             String tel=request.getParameter("tel");
             Tb_SpaceRes SpaceRes =new Tb_SpaceRes();
             SpaceRes.setid(id);
             SpaceRes.setname(name);
             SpaceRes.setarea(area);
             SpaceRes.setcomparyID(comparyID);
             SpaceRes.setcode(code);
             SpaceRes.setaddress(address);
             SpaceRes.setcharge(charge);
             SpaceRes.settel(tel);
           
   	      Tb_Message message=new Tb_Message();
   	    
            
              //最后调用服务来添加
             //String message="成功！"; 
             
              if (My_SpaceResService.insertData(SpaceRes)==true) { 
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
    	String id=request.getParameter("id");
   	    String name=request.getParameter("name");
   	    String area=request.getParameter("area");
        String comparyID=request.getParameter("comparyID");
        String code=request.getParameter("code");
        String address=request.getParameter("address");
        String charge=request.getParameter("charge");
        String tel=request.getParameter("tel");
   	 
   	 Tb_SpaceRes SpaceRes =new Tb_SpaceRes();
   	            SpaceRes.setid(id);
                SpaceRes.setname(name);
                SpaceRes.setarea(area);
                SpaceRes.setcomparyID(comparyID);
                SpaceRes.setcode(code);
                SpaceRes.setaddress(address);
                SpaceRes.setcharge(charge);
                SpaceRes.settel(tel);
   	   
   	   boolean b=  My_SpaceResService.updateData(SpaceRes);
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
                 String  id= request.getParameter("id");   
               
                  boolean  message=My_SpaceResService.deleteData(id); 
                  if (message==true) { 
                      queryAll(request, response); 
                 }
                  else {
                      
                      request.setAttribute("msg", "删除失败!!");
                      //request.getRequestDispatcher("/index.jsp").forward(request, response); 
                 }
          }

}
