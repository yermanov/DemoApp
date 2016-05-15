package com.mdevtunisia.manouba.demo.data.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mdevtunisia.manouba.demo.data.DBOpenHelper;

/**
 * Created by yermanov on 15/05/16.
 */
public class BaseDAO {

    public SQLiteDatabase getWritableDatabase(Context context) {
        return DBOpenHelper.getInstance(context)
                .getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase(Context context) {
        return DBOpenHelper.getInstance(context)
                .getReadableDatabase();
    }
}
