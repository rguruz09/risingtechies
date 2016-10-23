package com.risingtechies.intuithack.intuithack;

import java.util.Date;

/**
 * Created by Rekha on 10/22/2016.
 */
public class OrdersListInfo {

    private String customerName;
    private Date orderDate;

    public OrdersListInfo(String customerName, Date orderDate) {
        this.customerName = customerName;
        this.orderDate = orderDate;
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
}
