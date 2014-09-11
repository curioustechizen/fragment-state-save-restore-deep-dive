package com.github.curioustechizen.fragmentstate;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    private static final FlagshipDevice [] ITEMS = {
            new FlagshipDevice("Nexus 4", "Phone"),
            new FlagshipDevice("Nexus 7", "Tablet"),
            new FlagshipDevice("Nexus 10", "Tablet"),
            new FlagshipDevice("Nexus 5", "Phone"),
            new FlagshipDevice("Nexus 7 2013", "Tablet")
    };
    private static final String KEY_ADAPTER_STATE = "com.github.curioustechizen.fragmentstate.KEY_ADAPTER_STATE";

    private FlagshipDeviceAdapter mAdapter;


    public FirstFragment(){}

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ListView lv = (ListView) rootView.findViewById(android.R.id.list);

        //When we go to next fragment and return back here, the adapter is already present and populated.
        //Don't create it again in such cases. Hence the null check.
        if(mAdapter == null){
            mAdapter = new FlagshipDeviceAdapter(getActivity());
        }
        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_ADAPTER_STATE)){
            ArrayList<FlagshipDevice> savedAdapterState = savedInstanceState.getParcelableArrayList(KEY_ADAPTER_STATE);
            mAdapter.onRestoreInstanceState(savedAdapterState);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mAdapter != null){
            outState.putParcelableArrayList(KEY_ADAPTER_STATE, mAdapter.onSaveInstanceState());
        }
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

        ArrayList<FlagshipDevice> onSaveInstanceState(){
            int size = getCount();
            ArrayList<FlagshipDevice> items = new ArrayList<FlagshipDevice>(size);
            for(int i=0;i<size;i++){
                items.add(getItem(i));
            }
            return items;
        }

        void onRestoreInstanceState(ArrayList<FlagshipDevice> items){
            clear();
            addAll(items);
        }

    }

    static class FlagshipDevice implements Parcelable {
        String name;
        String formFactor;

        FlagshipDevice(String name, String formFactor){
            this.name = name;
            this.formFactor = formFactor;
        }

        protected FlagshipDevice(Parcel in) {
            name = in.readString();
            formFactor = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(formFactor);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<FlagshipDevice> CREATOR = new Parcelable.Creator<FlagshipDevice>() {
            @Override
            public FlagshipDevice createFromParcel(Parcel in) {
                return new FlagshipDevice(in);
            }

            @Override
            public FlagshipDevice[] newArray(int size) {
                return new FlagshipDevice[size];
            }
        };
    }
}
