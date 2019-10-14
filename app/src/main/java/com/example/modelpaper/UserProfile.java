package com.example.modelpaper;

import android.provider.BaseColumns;

public final class UserProfile {
    private UserProfile()
    {

    }

    public class Users implements BaseColumns{

        public final static String TABLE_NAME = "users";

        public final static String COLUMN_NAME_USERNAME = "userName";
        public final static String COLUMN_NAME_PASSWORD = "password";
        public final static String COLUMN_NAME_DOB = "dateOfBirth";
        public final static String COLUMN_NAME_GENDER = "gender";



    }
}
