package com.mdevtunisia.manouba.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mdevtunisia.manouba.demo.R;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.ui.fragment.PersonListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getIntent().getExtras() != null) {

            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    PersonListFragment.getInstance(getIntent().getExtras())).commit();
        }
    }
}
