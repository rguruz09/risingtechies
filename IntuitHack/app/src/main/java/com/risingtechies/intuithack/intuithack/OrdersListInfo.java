package com.risingtechies.intuithack.intuithack;

import java.util.Date;

/**
 * Created by Rekha on 10/22/2016.
 */
public class OrdersListInfo {

    private int id;
    private String customerName;
    private Date orderDate;
    private double price;

    public OrdersListInfo(String customerName, Date orderDate, double price) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
