package com.github.curioustechizen.fragmentstate;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class FirstFragment extends Fragment {

    private static final FlagshipDevice [] ITEMS = {
            new FlagshipDevice("Nexus 4", "Phone"),
            new FlagshipDevice("Nexus 7", "Tablet"),
            new FlagshipDevice("Nexus 10", "Tablet"),
            new FlagshipDevice("Nexus 5", "Phone"),
            new FlagshipDevice("Nexus 7 2013", "Tablet")
    };
    private FlagshipDeviceAdapter mAdapter;

    public FirstFragment(){}

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ListView lv = (ListView) rootView.findViewById(android.R.id.list);
        if(mAdapter == null){
            mAdapter = new FlagshipDeviceAdapter(getActivity());
        }
        lv.setAdapter(mAdapter);
        ((Button)rootView.findViewById(R.id.btn_populate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateList(ITEMS);
            }
        });
        return rootView;
    }

    private void populateList(FlagshipDevice ... items) {
        mAdapter.clear();
        mAdapter.addAll(items);
    }


    static class FlagshipDeviceAdapter extends ArrayAdapter<FlagshipDevice> {
        FlagshipDeviceAdapter(Context context){
            super(context, android.R.layout.simple_list_item_multiple_choice);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
            TextView tv = (TextView) v.findViewById(android.R.id.text1);
            FlagshipDevice item = getItem(position);
            tv.setText(item.name + " ("+item.formFactor+")");
            return v;
        }

    }

    static class FlagshipDevice {
        String name;
        String formFactor;

        FlagshipDevice(String name, String formFactor){
            this.name = name;
            this.formFactor = formFactor;
        }
    }
}
