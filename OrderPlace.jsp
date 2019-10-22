<%@page import="com.virtusa.daos.OrderDAO"%>
<%@page import="com.virtusa.daos.CustomerDAO"%>
<%@page import="com.virtusa.entity.CustomerDetails"%>
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
	Connection con;
    PreparedStatement pst;
    int pid=0; 
    con = DaoConnection.getConnection();
    String cmd = "select productId from product where productName=?";
    pst=con.prepareStatement(cmd);
    pst.setString(1, request.getParameter("productName"));
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
    pid=rs.getInt("productid");
    }
	int q =Integer.parseInt(request.getParameter("quantity"));
	String username=(String)session.getAttribute("username");
    CustomerDetails c=new CustomerDAO().searchCustomer(username);
    int id=c.getCustomerId();
 	OrderDAO dao = new OrderDAO();
	out.println(dao.orderPlace(pid,q,id));
	%>
	<br><br>
	<form action="trans.html">
	<center>
	<input type="submit" value="pay bill" style="background-color: gray;" />
	</center>
	</form>
</body>
</html>
