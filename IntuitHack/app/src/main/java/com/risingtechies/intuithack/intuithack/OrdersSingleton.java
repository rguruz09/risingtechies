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

        OrdersListInfo order1 = new OrdersListInfo("Rekha", new Date(),123.2);
        OrdersListInfo order2 = new OrdersListInfo("Sanjeedha", new Date(), 70.0);
        OrdersListInfo order3 = new OrdersListInfo("Raghu", new Date(), 83.45);
        OrdersListInfo order4 = new OrdersListInfo("Vinay", new Date(), 120.0);

        ordersListInfos.add(order1);
        ordersListInfos.add(order2);
        ordersListInfos.add(order3);
        ordersListInfos.add(order4);

    }

    /*private FavSingleton(Context appContext) {
        mAppContext = appContext;
        restaurantAttrs = new ArrayList<RestaurantAttr>();
    }*/

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


}
