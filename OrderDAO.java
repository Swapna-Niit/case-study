package com.virtusa.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.mysql.cj.protocol.Resultset;
import com.virtusa.DaoConnection.DaoConnection;
import com.virtusa.entity.OrderDetails;
import com.virtusa.entity.ProductDetails;

public class OrderDAO {
Connection con;
PreparedStatement pst;
private static Logger logger =LogManager.getLogger(OrderDAO.class);
public String orderPlace(int productId,int qtyOrd,int customerId) {
        con=DaoConnection.getConnection();
        String res="";
        ProductDetails product= new ProductDAO().searchProductDetails(productId);
       // CustomerDetails customer = new CustomerDetails();
        OrderDetails order = new OrderDetails();
        if(order!=null) {
            int orderqty=product.getProductStock();
            if(orderqty>=qtyOrd) {
                String cmd="insert into Orders(orderId,productId,qtyord,billamt,customerId)values (?,?,?,?,?)";
                try {
                    int orderid=generateOrderId();
                    pst=con.prepareStatement(cmd);
                  //  int ordId;
                    pst.setInt(1, orderid);
                    int pid= product.getProductId();
                    pst.setInt(2,pid);
                    int qtyord=order.getQtyOrd();
                    pst.setInt(3, qtyOrd );                    
                    double bill=qtyOrd*(product.getProductPrice());
                    pst.setDouble(4,bill);
                    pst.setInt(5, customerId);
                    pst.executeUpdate();
                   cmd="insert into Transactions(transactionId,orderTotalAmount)values (?,?)";
                    pst=con.prepareStatement(cmd);
                    pst.setInt(1,new TransactionDAO().generateTransactionid());
                    double orderTotalAmount=bill;
                    pst.setDouble(2, orderTotalAmount);
                    pst.executeUpdate();
                    res="order placed";
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                   // e.printStackTrace();
                   // res=e.getMessage();
                logger.error("error with SQL", e);
                }
            }else {
                res="insufficient quantity";
            }
        }else {
            res="no stock";
        }
        return res;
    }
public int generateOrderId()
{
con=DaoConnection.getConnection();
String cmd="select case when max(orderId) is NULL then 1 else max(orderId)+1 end ordid from orders";
int orderId=0;
try {
pst= con.prepareStatement(cmd);
ResultSet rs = pst.executeQuery();
while(rs.next())
{
orderId =rs.getInt("ordid");
}
} catch (SQLException e) {
// TODO Auto-generated catch block
//e.printStackTrace();
logger.error("error with SQL", e);
}
return orderId;
}
}
