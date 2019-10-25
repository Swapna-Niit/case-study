package com.virtusa.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.virtusa.DaoConnection.DaoConnection;
import com.virtusa.entity.CustomerDetails;

public class CustomerDAO {
Connection con;
PreparedStatement pst;
private static Logger logger =LogManager.getLogger(CustomerDAO.class);
public String updatePassword(String customerEmail,String customerPassword) {
        String res="";
        con=DaoConnection.getConnection();
        String cmd="update customers set customerPassword=? where customerEmail=?";
        CustomerDAO dao = new CustomerDAO();
        CustomerDetails customer=dao.searchCustomer(customerEmail);
        if(customer!=null) {
            try {
                pst=con.prepareStatement(cmd);
                pst.setString(1, customerPassword);
                pst.setString(2, customerEmail);
                pst.executeUpdate();
                res="password Updated";
            } catch (SQLException e) {
                // TODO Auto-generated catch block
              //  e.printStackTrace();
                logger.error("error with SQL", e);
            }
        }
            return res;          
        }
public CustomerDetails searchCustomer(String customerEmail)
{
con=DaoConnection.getConnection();
String cmd= "select * from customers where customerEmail=?";
CustomerDetails customer = null;
try {
pst=con.prepareStatement(cmd);
pst.setString(1,customerEmail);
ResultSet rs = pst.executeQuery();
if(rs.next())
{
customer = new CustomerDetails();
customer.setCustomerEmail(rs.getString("customerEmail"));
customer.setCustomerPassword(rs.getString("customerPassword"));
customer.setCustomerId(rs.getInt("customerId"));
}

} catch (SQLException e) {
// TODO Auto-generated catch block
//e.printStackTrace();
logger.error("error with SQL", e);
}
return customer;
}
public String createCustomer(CustomerDetails customer)
{
con=DaoConnection.getConnection();
String cmd="insert into customers(customerId,customerName,customerEmail,customerPassword,"
+ "customerDOB,customerAddress,customerContact,customerState,customerCity)values(?,?,?,?,?,?,?,?,?) ";
String res="";
try {
pst =con.prepareStatement(cmd);
int id=new CustomerDAO().generateCustomerId();
pst.setInt(1, id);
pst.setString(2, customer.getCustomerName());
pst.setString(3, customer.getCustomerEmail());
pst.setString(4, customer.getCustomerPassword());
java.sql.Date sqlDate = new java.sql.Date(customer.getCustomerDOB().getTime());
             pst.setDate(5, sqlDate);
pst.setString(6, customer.getCustomerAddress());
pst.setLong(7, customer.getCustomerContact());
pst.setString(8, customer.getCustomerState());
pst.setString(9, customer.getCustomerCity());
pst.executeUpdate();
res="customer added successfully";
} catch (SQLException e) {
// TODO Auto-generated catch block
// e.printStackTrace();
logger.error("error with SQL", e);
}catch(NullPointerException e) {
//e.printStackTrace();
logger.error("Null pointer Exception", e);
}
return res;
}
public int generateCustomerId()
{
con=DaoConnection.getConnection();
String cmd= "select case when max(customerId) is NULL "
+ " THEN 1 ELSE Max(customerId)+1 END cid from customers";
int customerId=0;
try {
pst = con.prepareStatement(cmd);
ResultSet rs = pst.executeQuery();
while(rs.next())
{
customerId=rs.getInt("cid");
}
} catch (SQLException e) {
// TODO Auto-generated catch block
//e.printStackTrace();
logger.error("error with SQL", e);
}
return customerId;
}
}
