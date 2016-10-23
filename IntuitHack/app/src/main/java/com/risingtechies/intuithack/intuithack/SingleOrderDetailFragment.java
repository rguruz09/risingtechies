package com.risingtechies.intuithack.intuithack;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.sdk.register.ChargeRequest;
import com.squareup.sdk.register.RegisterClient;
import com.squareup.sdk.register.RegisterSdk;

import static com.squareup.sdk.register.CurrencyCode.USD;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import static com.squareup.sdk.register.CurrencyCode.USD;

/**
 * Created by Rekha on 10/22/2016.
 */
public class SingleOrderDetailFragment extends ListFragment{

    public static final String EXTRA_ID =
            "com.risingtechies.intuithack.intuithack.ID";

    private TextView socustomername;
    private TextView soorderid;
    private TextView sodate;
    private TextView sotime;
    private TextView totalcost;
    private ListView lv;
    private Button proceed;
    private Button cancel;
    private static int id;
    private ArrayList<SingleOrderCartInfo> sod;
    private OrdersListInfo oli;
    double amount = 0;
    private RegisterClient registerClient;
    private static final String YOUR_CLIENT_ID = "sq0idp-yeTIZNT_Kk9s8ErUUd1JyA";
    private int CHARGE_REQUEST_CODE = 1;
    private static String name = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        id = Integer.parseInt(getArguments().getSerializable(EXTRA_ID).toString());
        oli = OrdersSingleton.get(getActivity()).getOrdersListInfo(id);

        SingleOrderCartList s = new SingleOrderCartList();
        sod = s.getSingleOrderCartInfo();

        for(SingleOrderCartInfo singleOrderCartInfo:sod){
            amount += singleOrderCartInfo.getTotalCost();
        }
        CHARGE_REQUEST_CODE = (int)amount*100;


        CartListAdapter adapter = new CartListAdapter(sod);
        setListAdapter(adapter);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        name = oli.getCustomerName();
        View v = inflater.inflate(R.layout.single_order_fragment, parent, false);
        socustomername = (TextView)v.findViewById(R.id.socustomername);
        socustomername.setText("Customer: " +oli.getCustomerName());
        sodate = (TextView)v.findViewById(R.id.sodate);
        Date oDate = oli.getOrderDate();
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String date1 = dateInstance.format(oDate);
        DateFormat timeInstance = SimpleDateFormat.getTimeInstance();
        String date2 = timeInstance.format(oDate);
        sodate.setText("Ordered On: " +date1);
        sotime = (TextView)v.findViewById(R.id.sotime);
        sotime.setText("Time: " +date2);
        totalcost = (TextView)v.findViewById(R.id.totalcost);
        totalcost.setText("Total Cost: "+ String.valueOf(amount));

        ListView lv = (ListView)v.findViewById(android.R.id.list);

        proceed = (Button)v.findViewById(R.id.proceedbtn);
        cancel = (Button)v.findViewById(R.id.cancelbtn);

        proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v)
            {
                try {

                    startTransaction();

                }catch (Exception e){
                    Log.e("API_CALL", e.getLocalizedMessage());
                }

            }
        });

        return v;
    }

    /*public class UrlActivity extends AsyncTask<String,Void,Bitmap[]> {

        private Exception exception;
        public Bitmap[] doInBackground(String... urls){
            Bitmap[] mybitmap = new Bitmap[3];

            try {
                for(int i=0; i<3; i++) {
                    URL url = new URL(urls[i]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    mybitmap[i] = BitmapFactory.decodeStream(input);
                }
                return mybitmap;
            } catch (IOException e) {
                // Log exception
                this.exception = e;
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap[] mybitmap) {
            locationMap.setImageBitmap(mybitmap[0]);
            ratingUrl.setImageBitmap(mybitmap[1]);
            snippetUrl.setImageBitmap(mybitmap[2]);
        }
    }*/

    public class CartListAdapter extends ArrayAdapter<SingleOrderCartInfo> {

        public CartListAdapter(ArrayList<SingleOrderCartInfo> singleOrderCartInfos) {
            super(getActivity(), 0, singleOrderCartInfos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.cart_list_display, null);
            }


            // Replace YOUR_CLIENT_ID with your Square-assigned client application ID,
            // available from the application dashboard.
            registerClient = RegisterSdk.createClient(getActivity(), YOUR_CLIENT_ID);


            SingleOrderCartInfo singleOrderCartInfo = getItem(position);

            ImageView cartIcon = (ImageView) convertView.findViewById(R.id.cartIcon);
            new UrlActivity2().execute(convertView, singleOrderCartInfo.getItemurl());
            TextView productName = (TextView) convertView.findViewById(R.id.productName);
            productName.setText(singleOrderCartInfo.getItemName());
            TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
            String qty = "Quantity: " + Integer.toString(singleOrderCartInfo.getItemQty());
            quantity.setText(qty);
            TextView cost = (TextView) convertView.findViewById(R.id.cost);
            String cst = "$"+Double.toString(singleOrderCartInfo.getCost());
            cost.setText(cst);
            return convertView;
        }

        public class UrlActivity2 extends AsyncTask<Object, Void, Bitmap> {
            private Exception exception;
            Bitmap mybitmaprating;
            View myview;

            public Bitmap doInBackground(Object... parameters) {
                myview = (View) parameters[0];
                try {

                        String url1 = (String) parameters[1];
                        URL url = new URL(url1);
                       /* HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        mybitmaprating = BitmapFactory.decodeStream(input);*/

                        try {
                            InputStream in = new java.net.URL(url1).openStream();
                            mybitmaprating = BitmapFactory.decodeStream(in);
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage());
                            e.printStackTrace();
                        }


                    return mybitmaprating;
                } catch (IOException e) {
                    // Log exception
                    this.exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmaps) {
                ImageView cartIcon = (ImageView) myview.findViewById(R.id.cartIcon);
                cartIcon.setImageBitmap(bitmaps);
            }
        }

    }

    public void startTransaction() {
        ChargeRequest request = new ChargeRequest.Builder(CHARGE_REQUEST_CODE, USD).build();
        try {
            Intent intent = registerClient.createChargeIntent(request);
            startActivityForResult(intent, CHARGE_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            showDialog("Error", "Square Register is not installed", null);
            registerClient.openRegisterPlayStoreListing();
        }
    }

    public void showDialog(String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, listener)
                .show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHARGE_REQUEST_CODE) {
            if (data == null) {
                showDialog("Error", "Square Register was uninstalled or crashed", null);
                return;
            }

            if (resultCode == Activity.RESULT_OK) {
                ChargeRequest.Success success = registerClient.parseChargeSuccess(data);
                String message = "Client transaction id: " + success.clientTransactionId;
                showDialog("Success!", message, null);
                OrdersSingleton.get(getActivity()).removeOrdersListInfo(name);
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.mainpage_fragment,new OrdersFragment()).commit();

            } else {
                ChargeRequest.Error error = registerClient.parseChargeError(data);

                if (error.code == ChargeRequest.ErrorCode.TRANSACTION_ALREADY_IN_PROGRESS) {
                    String title = "A transaction is already in progress";
                    String message = "Please complete the current transaction in Register.";

                    showDialog(title, message, new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            // Some errors can only be fixed by launching Register
                            // from the Home screen.
                            registerClient.launchRegister();
                        }
                    });
                } else {
                    showDialog("Error: " + error.code, error.debugDescription, null);
                }
            }
        }
    }

}
