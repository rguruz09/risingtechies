package com.risingtechies.intuithack.intuithack;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rekha on 10/22/2016.
 */
public class SingleOrderDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String orderid = getIntent().getExtras()
                .getSerializable(SingleOrderDetailFragment.EXTRA_ID).toString();
        setContentView(R.layout.single_order_activity);
        /*FragmentManager fm = getFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable(SingleOrderDetailFragment.EXTRA_ID, orderid);
        fm.beginTransaction().replace(R.id.mainpage_fragment, new SingleOrderDetailFragment()).commit();*/

        SingleOrderDetailFragment fragment = new SingleOrderDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(SingleOrderDetailFragment.EXTRA_ID, orderid);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.detailSingleOrder,fragment).commit();
    }

}
