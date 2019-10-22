<%@page import="com.virtusa.daos.TransactionDAO"%>
<%@page import="com.virtusa.DaoConnection.DaoConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
Connection con = DaoConnection.getConnection();
int id = new TransactionDAO().search();
String cmd = "update transactions set status ='complete' where transactionId=?";
PreparedStatement pst=con.prepareStatement(cmd);
pst.setInt(1,id);
pst.executeUpdate();
out.println("payment completed, thanks for shopping in shoppersden");
%>
</body>
</html>
