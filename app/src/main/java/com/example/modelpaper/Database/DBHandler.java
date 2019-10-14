package com.example.modelpaper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.modelpaper.UserProfile;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_create_statement = "CREATE TABLE "+ UserProfile.Users.TABLE_NAME+ "( "+
                UserProfile.Users._ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                UserProfile.Users.COLUMN_NAME_USERNAME+" TEXT, "+
                UserProfile.Users.COLUMN_NAME_PASSWORD+" TEXT, "+
                UserProfile.Users.COLUMN_NAME_DOB+" TEXT, "+
                UserProfile.Users.COLUMN_NAME_GENDER+" TEXT"+
                ")";

        db.execSQL(sql_create_statement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+UserProfile.Users.TABLE_NAME;
        db.execSQL(query);
    }

    public boolean addInfo(String username, String password, String dob, String gender){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.COLUMN_NAME_USERNAME, username);
        contentValues.put(UserProfile.Users.COLUMN_NAME_PASSWORD, password);
        contentValues.put(UserProfile.Users.COLUMN_NAME_DOB, dob);
        contentValues.put(UserProfile.Users.COLUMN_NAME_GENDER, gender);

        long result = sqLiteDatabase.insert(UserProfile.Users.TABLE_NAME, null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean updateInfor(String id, String username, String password, String dob, String gender){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.COLUMN_NAME_USERNAME, username);
        contentValues.put(UserProfile.Users.COLUMN_NAME_PASSWORD, password);
        contentValues.put(UserProfile.Users.COLUMN_NAME_DOB, dob);
        contentValues.put(UserProfile.Users.COLUMN_NAME_GENDER, gender);

        String whereClause = UserProfile.Users._ID+" = ?";
        String[] whereArgs = {id};

        int result = sqLiteDatabase.update(UserProfile.Users.TABLE_NAME, contentValues, whereClause, whereArgs);

        if(result == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor readAllInfor()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sql = "SELECT * FROM "+ UserProfile.Users.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        return cursor;
    }

    public Cursor readAllInfor(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sql = "SELECT * FROM "+ UserProfile.Users.TABLE_NAME+ " WHERE "+ UserProfile.Users._ID +" = ?";
        String[] selectionArgs = {id};

        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);

        return cursor;
    }

    public boolean deleteInfor(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String whereClause = UserProfile.Users._ID+" = ?";
        String[] whereArgs = {id};

        int result = sqLiteDatabase.delete(UserProfile.Users.TABLE_NAME, whereClause, whereArgs);

        if(result == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
