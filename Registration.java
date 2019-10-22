package com.virtusa.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.daos.CustomerDAO;
import com.virtusa.entity.CustomerDetails;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDetails c=new CustomerDetails();
		c.setCustomerName(request.getParameter("customername"));
		c.setCustomerEmail(request.getParameter("customerEmail"));
		c.setCustomerPassword(request.getParameter("customerPassword"));
		String dob=request.getParameter("date_of_birth");
        java.sql.Date dob1=java.sql.Date.valueOf(dob);
        c.setCustomerDOB(dob1);
		c.setCustomerAddress(request.getParameter("customerAddress"));
		c.setCustomerContact(Integer.parseInt(request.getParameter("customerContact")));
		c.setCustomerCity(request.getParameter("customerCity"));
		c.setCustomerState(request.getParameter("customerState"));
		CustomerDAO cd=new CustomerDAO();
		PrintWriter out=response.getWriter();
		out.println(cd.createCustomer(c));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
