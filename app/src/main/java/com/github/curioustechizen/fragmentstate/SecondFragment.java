package com.github.curioustechizen.fragmentstate;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hugo.weaving.DebugLog;

public class SecondFragment extends Fragment{

    public SecondFragment(){}
    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @DebugLog
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @DebugLog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @DebugLog
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @DebugLog
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @DebugLog
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
