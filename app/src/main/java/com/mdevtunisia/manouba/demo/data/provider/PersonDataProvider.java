package com.mdevtunisia.manouba.demo.data.provider;

import android.content.Context;
import android.util.Log;

import com.mdevtunisia.manouba.demo.data.dao.PersonDAO;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.data.schema.TableUtil;

import java.util.List;

/**
 * Created by yermanov on 15/05/16.
 */
public class PersonDataProvider {

    private static final String TAG = PersonDataProvider.class.getSimpleName();

    private static PersonDataProvider ourInstance = null;

    private Context mContext;
    private PersonDAO mPersonDAO;

    public static PersonDataProvider getInstance(Context context) {

        if (ourInstance == null) {
            synchronized (PersonDataProvider.class) {
                if (ourInstance == null) {
                    ourInstance = new PersonDataProvider(context);
                }
            }
        }
        return ourInstance;
    }

    private PersonDataProvider(Context context) {
        mContext = context;
        mPersonDAO = new PersonDAO();
    }

    public List<Person> getPersonList() {
        return mPersonDAO.queryListOfPerson(mContext);
    }

    public void addListPerson(List<Person> personList) {

        if (personList == null) {
            Log.w(TAG, "personList is null!");
            return;
        }

        mPersonDAO.insertListPerson(mContext, personList);
    }

    public void addPerson(Person person) {

        if (person == null) {
            Log.w(TAG, "person is null!");
            return;
        }

        mPersonDAO.insertPerson(mContext, person);
    }
}
