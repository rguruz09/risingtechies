package com.risingtechies.intuithack.intuithack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

/**
 * Created by Rekha on 10/23/2016.
 */
public class MainWebViewFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View wbview = inflater.inflate(R.layout.main_webview_fragment,container,false);
        WebView myWebView = (WebView) wbview.findViewById(R.id.webview);
        myWebView.loadUrl("http://192.168.239.86:3030");
        return wbview;
    }


}
