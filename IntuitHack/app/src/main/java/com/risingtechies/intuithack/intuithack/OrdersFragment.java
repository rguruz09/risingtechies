package com.risingtechies.intuithack.intuithack;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rekha on 10/22/2016.
 */
public class OrdersFragment extends ListFragment {
    private ArrayList<OrdersListInfo> ordersListInfos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ordersListInfos = OrdersSingleton.get(getActivity()).getOrdersListInfos();

        OrdersAdapter adapter = new OrdersAdapter(ordersListInfos);
        setListAdapter(adapter);
        //new UrlActivity().execute(restaurantAttrs);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ordersview = inflater.inflate(R.layout.fragment_orders,container,false);
        ListView lv = (ListView)ordersview.findViewById(android.R.id.list);
        return ordersview;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        OrdersListInfo od = ((OrdersAdapter)getListAdapter()).getItem(position);
        int orderid = od.getId();
        /*Intent i = new Intent(getActivity().getApplicationContext(), SingleOrderDetail.class);
        i.putExtra(SingleOrderDetailFragment.EXTRA_ID, orderid);
        startActivity(i);*/

        SingleOrderDetailFragment fragment = new SingleOrderDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(SingleOrderDetailFragment.EXTRA_ID, orderid);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.mainpage_fragment,fragment).commit();

        /*Fragment detailfrag = new DetailFragment();
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft=fm.beginTransaction();
        Bundle args = new Bundle();
        args.putString("UID", uid.toString());
        detailfrag.setArguments(args);
        ft.replace(R.id.search_container, detailfrag);
        ft.commit();*/
    }


    public class OrdersAdapter extends ArrayAdapter<OrdersListInfo> {

        public OrdersAdapter(ArrayList<OrdersListInfo> ordersListInfos) {
            super(getActivity(), 0, ordersListInfos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.orders_list_display, null);
            }

            OrdersListInfo ordersListInfo = getItem(position);

            TextView customerName = (TextView) convertView.findViewById(R.id.customerName);
            customerName.setText(ordersListInfo.getCustomerName());
            TextView date = (TextView) convertView.findViewById(R.id.date);
            date.setText(ordersListInfo.getOrderDate().toString());

            return convertView;
        }
    }
}
