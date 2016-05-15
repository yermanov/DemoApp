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
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.ui.adapter.PersonAdapter;

import java.util.ArrayList;

/**
 * Created by yermanov on 17/04/16.
 */
public class PersonListFragment extends Fragment {

    private ListView mListView;
    private PersonAdapter mPersonAdapter;

    private ArrayList<Person> mPersonArrayList;

    public static PersonListFragment getInstance(Bundle bundle) {
        PersonListFragment personListFragment =
                new PersonListFragment();

        personListFragment.setArguments(bundle);

        return personListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            mPersonArrayList = bundle.getParcelableArrayList("PersonList");
        }
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

        initEvent();

        populateListView();
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
        mPersonAdapter = new PersonAdapter(getContext(), mPersonArrayList);
        mListView.setAdapter(mPersonAdapter);
    }
}
