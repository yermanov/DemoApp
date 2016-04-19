package com.mdevtunisia.manouba.demo.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mdevtunisia.manouba.demo.R;
import com.mdevtunisia.manouba.demo.model.Person;
import com.mdevtunisia.manouba.demo.parser.PersonParser;
import com.mdevtunisia.manouba.demo.service.WebService;
import com.mdevtunisia.manouba.demo.ui.adapter.PersonAdapter;
import com.mdevtunisia.manouba.demo.util.StringUtil;

import java.util.List;

/**
 * Created by yermanov on 17/04/16.
 */
public class PersonListFragment extends Fragment {

    private ListView mListView;
    private PersonAdapter mPersonAdapter;

    private PersonTask mPersonTask;
    private ProgressDialog mProgressDialog;

    public static PersonListFragment getInstance() {
        PersonListFragment personListFragment =
                new PersonListFragment();

        return personListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_persons);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        initEvent();

        populateListView();
    }

    private void initView()
    {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCanceledOnTouchOutside(true);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (mPersonTask != null) {
                    mPersonTask.cancel(true);
                    mPersonTask = null;
                }
            }
        });
    }

    private void initEvent()
    {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Person person = mPersonAdapter.getItem(position);
                Toast.makeText(getContext(), person.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateListView()
    {
        mPersonTask = new PersonTask();
        mPersonTask.execute();
    }

    private class PersonTask extends AsyncTask<Void, Void, List<Person>> {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            mProgressDialog.show();
        }

        @Override
        protected List<Person> doInBackground(Void... params)
        {
            String json = WebService.getPersonList(getContext().getString(R.string.person_url));

            List<Person> personList = null;

            if (StringUtil.isEmpty(json) == false) {
                personList = PersonParser.parsePersonList(json);
            }

            return personList;
        }

        @Override
        protected void onPostExecute(List<Person> personList)
        {
            super.onPostExecute(personList);

            mProgressDialog.dismiss();

            mPersonAdapter = new PersonAdapter(getContext(), personList);
            mListView.setAdapter(mPersonAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPersonTask != null) {
            mPersonTask.cancel(true);
            mPersonTask = null;
        }
    }
}
