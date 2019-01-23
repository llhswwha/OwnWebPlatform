<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% 
response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
 String newLocn="jsp/index.jsp";
 response.setHeader("Location",newLocn); 
%>
