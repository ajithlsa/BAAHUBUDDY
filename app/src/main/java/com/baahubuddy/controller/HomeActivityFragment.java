package com.baahubuddy.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.baahubuddy.R;
import com.baahubuddy.WebInterface.AddCustomerInterface;
import com.baahubuddy.WebInterface.HomeInterface;


/**
 * Created by Ajith Lal on 5/6/2018.
 */

public class HomeActivityFragment extends Fragment {

    WebView homeWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_home_activity, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
//        getActivity().setTitle(R.string.home_fragment_title);

        homeWebView = view.findViewById(R.id.homeWebView);
        homeWebView.getSettings().setJavaScriptEnabled(true);
        homeWebView.addJavascriptInterface(new HomeInterface(BaahubuddyActivity.context),"HomeInterface");
        homeWebView.loadUrl("file:///android_asset/views/Home.html");

        FloatingActionButton fab = view.findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeWebView.addJavascriptInterface(new AddCustomerInterface(BaahubuddyActivity.context),"AddCustomerInterface");
                homeWebView.loadUrl("file:///android_asset/views/AddCustomer.html");
            }
        });
    }
}