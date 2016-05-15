package com.mdevtunisia.manouba.demo.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mdevtunisia.manouba.demo.data.DBOpenHelper;
import com.mdevtunisia.manouba.demo.data.model.Person;
import com.mdevtunisia.manouba.demo.data.schema.TableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yermanov on 15/05/16.
 */
public class PersonDAO extends BaseDAO {

    private static final String TAG = PersonDAO.class.getSimpleName();

    public long insertPerson(Context context, Person person) {

        if (person == null) {
            Log.w(TAG, "Person is null!");
            return -1;
        }

        SQLiteDatabase db = getWritableDatabase(context);

        return db.insert(TableUtil.PersonTable.TABLE_NAME, null, person.getContentValues());
    }

    public void insertListPerson(Context context, List<Person> personList) {

        if (personList == null) {
            Log.w(TAG, "Person list is null!");
            return;
        }

        for (int i = 0; i< personList.size() ; i++) {
            insertPerson(context, personList.get(i));
        }
    }

    public List<Person> queryListOfPerson(Context context) {

        SQLiteDatabase db = getReadableDatabase(context);

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * ");
        sb.append(" FROM ");
        sb.append(" " + TableUtil.PersonTable.TABLE_NAME);

        String sql = sb.toString();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor == null) {
            Log.w(TAG, "Cursor is null!");
            return null;
        }

        List<Person> personList = null;

        while (cursor.moveToNext()) {

            Person person = new Person(cursor);

            if (personList == null) {
                personList = new ArrayList<>();
            }

            personList.add(person);
        }

        cursor.close();

        return personList;
    }
}
