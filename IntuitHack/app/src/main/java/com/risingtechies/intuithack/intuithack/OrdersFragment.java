package com.risingtechies.intuithack.intuithack;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Typeface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        //new UrlActivity().execute(ordersListInfos);

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
            //customerName.setTypeFace(Typeface.SANS_SERIF);
            customerName.setTextColor(Color.BLACK);
            customerName.setTextSize(20);

            TextView date = (TextView) convertView.findViewById(R.id.date);
            date.setTextColor(Color.BLACK);
            TextView time = (TextView) convertView.findViewById(R.id.time);
            time.setTextColor(Color.BLACK);
            Date oDate = ordersListInfo.getOrderDate();
            DateFormat dateInstance = SimpleDateFormat.getDateInstance();
            String date1 = dateInstance.format(oDate);
            DateFormat timeInstance = SimpleDateFormat.getTimeInstance();
            String date2 = timeInstance.format(oDate);

            date.setText("Ordered On: " +date1);
            time.setText("Time: " +date2);
            TextView price = (TextView)convertView.findViewById(R.id.price);
            price.setText(String.valueOf("$"+ ordersListInfo.getPrice()));
            price.setTextColor(Color.BLACK);

            return convertView;
        }
    }

    /*public class UrlActivity extends AsyncTask<Object, Void, Bitmap> {
        private Exception exception;
        Bitmap mybitmap;
        View myview;

        public Bitmap doInBackground(Object... parameters) {
            myview = (View) parameters[0];
            try {

                for (int i = 0; i < 2; i++) {
                    String urlStrng = (String) parameters[1];
                    URL url = new URL(urlStrng);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    mybitmap = BitmapFactory.decodeStream(input);

                }
                return mybitmap;
            } catch (IOException e) {
                // Log exception
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmaps) {
            ImageView restoIcon = (ImageView) myview.findViewById(R.id.restoIcon);
            restoIcon.setImageBitmap(bitmaps);
        }
    }*/
}
