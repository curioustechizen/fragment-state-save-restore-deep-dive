package com.github.curioustechizen.fragmentstate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, FirstFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onBtnClick(View v){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
