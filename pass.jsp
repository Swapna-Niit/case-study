
<%@page import="com.virtusa.daos.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd
">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="pass.jsp" method="get" >
CustomerEmail: <input type="text" name="customeremail"/><br/>
<br/>
New Password:<input type="text" name="customerpassword"/><br/>
<br/>
Re-type Password: <input type="text" name="RePassword"/><br/>
<br/>
<input type="submit" value="submit"/>
</form>
<%
CustomerDAO dao = new CustomerDAO();
String password=request.getParameter("customerpassword");
String rpassword=request.getParameter("RePassword");
if(password!=null && rpassword!=null){
   
    if(password.equals(rpassword)) {
        out.println(dao.updatePassword(request.getParameter("customeremail"),request.getParameter("customerpassword")));    
    }else{
        out.println("password mismatch..");
    }
}
%>
</body>
</body>
</html>
