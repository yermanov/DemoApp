package com.mdevtunisia.manouba.demo.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mdevtunisia.manouba.demo.R;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.data.provider.PersonDataProvider;
import com.mdevtunisia.manouba.demo.parser.PersonParser;
import com.mdevtunisia.manouba.demo.service.WebService;
import com.mdevtunisia.manouba.demo.ui.adapter.PersonAdapter;
import com.mdevtunisia.manouba.demo.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yermanov on 15/05/16.
 */
public class SplashScreenActivity extends AppCompatActivity {

    private PersonTask mPersonTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mPersonTask = new PersonTask();
        mPersonTask.execute();
    }

    private class PersonTask extends AsyncTask<Void, Void, List<Person>> {

        @Override
        protected List<Person> doInBackground(Void... params)
        {
            List<Person> personList = PersonDataProvider.getInstance(SplashScreenActivity.this).getPersonList();

            if (personList != null) {
                return personList;
            }

            String json = WebService.getPersonList(SplashScreenActivity.this.getString(R.string.person_url));

            if (StringUtil.isEmpty(json) == false) {
                personList = PersonParser.parsePersonList(json);
            }

            if (personList != null) {
                PersonDataProvider.getInstance(SplashScreenActivity.this).addListPerson(personList);
            }

            return personList;
        }

        @Override
        protected void onPostExecute(List<Person> personList)
        {
            super.onPostExecute(personList);

            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            intent.putParcelableArrayListExtra("PersonList", new ArrayList<Person>(personList));

            startActivity(intent);

            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPersonTask != null) {
            mPersonTask.cancel(true);
            mPersonTask = null;
        }
    }
}
