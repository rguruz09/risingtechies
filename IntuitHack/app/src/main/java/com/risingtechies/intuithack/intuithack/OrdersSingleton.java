package com.risingtechies.intuithack.intuithack;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
/**
 * Created by Rekha on 10/22/2016.
 */
public class OrdersSingleton {

    private static OrdersSingleton sOrdersSingleton;
    private Context mAppContext;
    private ArrayList<OrdersListInfo> ordersListInfos;

    private OrdersSingleton(Context appContext) {
        mAppContext = appContext;
        ordersListInfos = new ArrayList<OrdersListInfo>();

        OrdersListInfo order1 = new OrdersListInfo("Raghu Guru", new Date(),57.0);
        OrdersListInfo order2 = new OrdersListInfo("Sanjeedha Sanofer", new Date(), 70.0);
        OrdersListInfo order3 = new OrdersListInfo("Rekha Shankar", new Date(), 83.45);
        OrdersListInfo order4 = new OrdersListInfo("Vinay Hiremath", new Date(), 120.0);
        OrdersListInfo order5 = new OrdersListInfo("Sharath Nagendra", new Date(), 45.0);
        OrdersListInfo order6 = new OrdersListInfo("Nikhil Gowda", new Date(), 25.0);
        OrdersListInfo order7 = new OrdersListInfo("Shiv Karthey", new Date(), 75.0);
        OrdersListInfo order8 = new OrdersListInfo("Shruthi Navale", new Date(), 75.0);
        OrdersListInfo order9 = new OrdersListInfo("Swathi Sudarshan", new Date(), 46);

        ordersListInfos.add(order1);
        ordersListInfos.add(order2);
        ordersListInfos.add(order3);
        ordersListInfos.add(order4);
        ordersListInfos.add(order5);
        ordersListInfos.add(order6);
        ordersListInfos.add(order7);
        ordersListInfos.add(order8);
        ordersListInfos.add(order9);

    }

    /*private OrdersSingleton(Context appContext) {
        mAppContext = appContext;
        ordersListInfos = new ArrayList<OrdersListInfo>();
    }*/

    public void clearList(){
        ordersListInfos.clear();
    }


    public static OrdersSingleton get(Context c) {
        if (sOrdersSingleton == null) {
            sOrdersSingleton = new OrdersSingleton(c.getApplicationContext());
        }
        return sOrdersSingleton;
    }

    public ArrayList<OrdersListInfo> getOrdersListInfos(){
        return ordersListInfos;
    }

    public OrdersListInfo getOrdersListInfo(int position){
        for(OrdersListInfo od: ordersListInfos){
            if (od.getId() == position){
                return od;
            }
        }
        return null;
    }

    public void removeOrdersListInfo(String name){
        for(int i=0; i<ordersListInfos.size(); i++){
            if (ordersListInfos.get(i).getCustomerName().equals(name)){
                ordersListInfos.remove(i);
                break;
            }
        }
    }


}
