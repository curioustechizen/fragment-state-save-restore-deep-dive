package com.github.curioustechizen.fragmentstate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FirstFragment extends Fragment {

    public FirstFragment(){}

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ListView lv = (ListView) rootView.findViewById(android.R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                getResources().getStringArray(R.array.flagships)));
        return rootView;
    }
}
