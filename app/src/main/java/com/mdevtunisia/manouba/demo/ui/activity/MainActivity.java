package com.mdevtunisia.manouba.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mdevtunisia.manouba.demo.R;
import com.mdevtunisia.manouba.demo.ui.fragment.PersonListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.container,
                PersonListFragment.getInstance()).commit();
    }
}
