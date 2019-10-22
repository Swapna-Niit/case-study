<%@page import="com.virtusa.entity.ProductDetails"%>
<%@page import="com.virtusa.daos.ProductDAO"%>
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
int productId =Integer.parseInt(request.getParameter("productId"));
String cmd = "select * from product where productId=?";
PreparedStatement pst = con.prepareStatement(cmd);
pst.setInt(1,productId);
ResultSet rs = pst.executeQuery();
rs.next();
%>
<form method="get" action="UpdateProduct.jsp">
<center>
productId:
<input type="text" name = "productId" value="<%=rs.getInt("productId")%>" readonly="readonly"/>
<br><br>
productName:
<input type="text" value=<%=rs.getString("productName")%> name = "productName"/> <br><br>
productPrice:
<input type="text" value=<%=rs.getDouble("productPrice")%> name = "productPrice"/> <br><br>
productImage:
<input type="text" value=<%=rs.getString("productImage")%> name = "productImage"/> <br><br>
productDescription:
<input type="text" value=<%=rs.getString("productDescription")%> name = "productDescription"/> <br><br>
productStock:
<input type="text" value=<%=rs.getInt("productStock")%> name = "productStock"/> <br><br>
<input type="submit" value="update"/>
</center>
</form>
<%
 if((request.getParameter("productId")!=null) && request.getParameter("productPrice") != null)
 {
 	cmd= "update product set productName=?, productPrice=?, productImage=?, productDescription=?,productStock=? where productId=?";
 	pst = con.prepareStatement(cmd);
 	pst.setString(1, request.getParameter("productName"));
 	pst.setDouble(2, Double.parseDouble(request.getParameter("productPrice")));
 	pst.setString(3, request.getParameter("productImage"));
 	pst.setString(4, request.getParameter("productDescription"));
 	pst.setInt(5, Integer.parseInt(request.getParameter("productStock")));
 	pst.setInt(6,productId);
 	pst.executeUpdate();
// ProductDAO dao = new ProductDAO();
// ProductDetails pd = new ProductDetails();
// pd.setProductName(request.getParameter("productName"));
// pd.setProductPrice(Double.parseDouble(request.getParameter("productPrice")));
// pd.setProductImage(request.getParameter("productImage"));
// pd.setProductDescription(request.getParameter("productDescription"));
// pd.setProductStock(Integer.parseInt(request.getParameter("productStock")));
// out.println(dao.updateProductDetails(productId));
%>
<jsp:forward page="CRUD.jsp"></jsp:forward>
<%
 }
%>
</body>
</html>
