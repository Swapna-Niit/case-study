package com.virtusa.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.virtusa.DaoConnection.DaoConnection;
import com.virtusa.entity.ProductDetails;
public class ProductDAO {
	Connection con;
	PreparedStatement pst;
	Logger logger = Logger.getLogger(ProductDAO.class);
	
	public String updateProductDetails(int productId)
	{
		con = DaoConnection.getConnection();
		String res="";
		String cmd="update product set productName=?, productPrice=?, productImage=?, productDescription=?,productStock=? where productId=?";
		try {
			pst=con.prepareStatement(cmd);				
			ProductDetails product= new ProductDetails();
			pst.setString(1, product.getProductName());
			pst.setDouble(2, product.getProductPrice());
			pst.setString(3, product.getProductImage());
			pst.setString(4, product.getProductDescription());
			pst.setInt(5, product.getProductStock());
			pst.setInt(6, productId);
			pst.executeUpdate();
			res = "Product Details Updated";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}


