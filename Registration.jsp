<%@page import="com.virtusa.daos.CustomerDAO"%>
<%@page import="com.virtusa.entity.CustomerDetails"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
CustomerDetails c=new CustomerDetails();
c.setCustomerName(request.getParameter("customername"));
c.setCustomerEmail(request.getParameter("customerEmail"));
c.setCustomerPassword(request.getParameter("customerPassword"));
String dob=request.getParameter("customerDOB");
java.sql.Date dob1=java.sql.Date.valueOf(dob);
c.setCustomerDOB(dob1);
c.setCustomerAddress(request.getParameter("customerAddress"));
c.setCustomerContact(Integer.parseInt(request.getParameter("customerContact")));
c.setCustomerCity(request.getParameter("customerCity"));
c.setCustomerState(request.getParameter("customerState"));
CustomerDAO cd=new CustomerDAO();
out.print(cd.createCustomer(c));
%>
<input type="submit" value="go back to login page" onclick="window.location='frame3.html'">
</body>
</html>
