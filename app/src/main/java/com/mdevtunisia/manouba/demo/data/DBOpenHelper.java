package com.mdevtunisia.manouba.demo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mdevtunisia.manouba.demo.data.schema.TableUtil;

/**
 * Created by yermanov on 15/05/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static DBOpenHelper ourInstance = null;

    private Context mContext;

    public static DBOpenHelper getInstance(Context context) {

        if (ourInstance == null) {
            synchronized (DBOpenHelper.class) {
                if (ourInstance == null) {
                    ourInstance = new DBOpenHelper(context);
                }
            }
        }
        return ourInstance;
    }

    private DBOpenHelper(Context context) {
        super(context, TableUtil.DB_NAME, null, TableUtil.DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableUtil.create_person_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //        db.delete(TableUtil.DB_NAME, null, null);
    //        db.execSQL(TableUtil.create_person_table);
    }
}
