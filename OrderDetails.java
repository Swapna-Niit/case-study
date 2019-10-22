package com.virtusa.entity;

public class OrderDetails {

	private int orderId;
	private int productId;
	private int qtyOrd;
	private double billAmt;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQtyOrd() {
		return qtyOrd;
	}
	public void setQtyOrd(int qtyOrd) {
		this.qtyOrd = qtyOrd;
	}
	public double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}
}
