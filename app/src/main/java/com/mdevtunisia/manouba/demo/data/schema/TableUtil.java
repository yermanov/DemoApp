package com.mdevtunisia.manouba.demo.data.schema;

/**
 * Created by yermanov on 15/05/16.
 */
public class TableUtil {

    public static final String DB_NAME = "mdevtunisia.db";

    public static final int DB_VERSION = 1;

    public interface PersonTable {

        final String TABLE_NAME = "person";

        /**
         * PK : primary key auto increment
         */
        String COLUMN_ID = "id";

        /**
         * String : server id
         */
        String COLUMN_TOKEN = "token";

        /**
         * String : person first name
         */
        String COLUMN_FIRST_NAME = "first_name";

        /**
         * String : person last name
         */
        String COLUMN_LAST_NAME = "last_name";

        /**
         * String : person company
         */
        String COLUMN_COMPANY = "company";

        /**
         * String : person address
         */
        String COLUMN_ADDRESS = "address";

        /**
         * String : person email
         */
        String COLUMN_EMAIL = "email";

        /**
         * String : person phone
         */
        String COLUMN_PHONE = "phone";

        /**
         * Integer : person age
         */
        String COLUMN_AGE = "age";

        /**
         * String : person picture url
         */
        String COLUMN_PICTURE = "picture";
    }

    public static final String create_person_table =
            "CREATE TABLE " + PersonTable.TABLE_NAME
            + "("
                    + PersonTable.COLUMN_ID + " integer primary key autoincrement,"
                    + PersonTable.COLUMN_TOKEN + " text,"
                    + PersonTable.COLUMN_FIRST_NAME + " text,"
                    + PersonTable.COLUMN_LAST_NAME + " text,"
                    + PersonTable.COLUMN_ADDRESS + " text,"
                    + PersonTable.COLUMN_COMPANY + " text,"
                    + PersonTable.COLUMN_PHONE + " text,"
                    + PersonTable.COLUMN_PICTURE + " text,"
                    + PersonTable.COLUMN_EMAIL + " text,"
                    + PersonTable.COLUMN_AGE + " integer"
            + ");";
}
