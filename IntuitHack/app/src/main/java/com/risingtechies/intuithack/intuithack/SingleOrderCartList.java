package com.risingtechies.intuithack.intuithack;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rekha on 10/22/2016.
 */
public class SingleOrderCartList {

    private ArrayList<SingleOrderCartInfo> singleOrderCartInfos;

    public SingleOrderCartList() {
        singleOrderCartInfos = new ArrayList<SingleOrderCartInfo>();
        SingleOrderCartInfo order1 = new SingleOrderCartInfo("Turquoise Earrings","http://pad2.whstatic.com/images/thumb/f/f1/Make-Personality-Enhancing-Handmade-Accessories-Step-5.jpg/aid1362917-703px-Make-Personality-Enhancing-Handmade-Accessories-Step-5.jpg", 1, 45.00, 57.00);
        /*SingleOrderCartInfo order2 = new SingleOrderCartInfo("Bracelet", "https://bigskiesjewellery.files.wordpress.com/2013/02/example-of-a-good-jewellery-photo-1.jpg", 1, 34.00, 34.00);

        singleOrderCartInfos.add(order1);
        /*singleOrderCartInfos.add(order2);*/
        singleOrderCartInfos.add(order1);
    }

    /*private FavSingleton(Context appContext) {
        mAppContext = appContext;
        restaurantAttrs = new ArrayList<RestaurantAttr>();
    }*/


    public ArrayList<SingleOrderCartInfo> getSingleOrderCartInfo(){
        return singleOrderCartInfos;
    }


}
