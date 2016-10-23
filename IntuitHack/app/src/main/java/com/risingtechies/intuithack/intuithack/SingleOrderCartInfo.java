package com.risingtechies.intuithack.intuithack;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rekha on 10/22/2016.
 */
public class SingleOrderCartInfo {

    private String itemurl;
    private String itemName;
    private int itemQty;
    private double cost;
    private double totalCost;

    public SingleOrderCartInfo(String itemurl, String itemName, int itemQty, double cost, double totalCost) {
        this.itemurl = itemurl;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.cost = cost;
        this.totalCost = totalCost;
    }

    public String getItemurl() {
        return itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
