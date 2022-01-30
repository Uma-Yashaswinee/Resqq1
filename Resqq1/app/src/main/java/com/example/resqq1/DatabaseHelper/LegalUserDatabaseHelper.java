package com.example.resqq1.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.resqq1.Model.LegalUser;

import java.util.ArrayList;
import java.util.List;

public class LegalUserDatabaseHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "legal_user.db";

    // User table name
    private static final String TABLE_USER = "legal_user";

    //worker table columns
    private static final String COLUMN_LEGAL_USER_ID = "legal_user_id";
    private static final String COLUMN_LEGAL_USER_NAME = "legal_user_name";
    private static final String COLUMN_LEGAL_USER_EMAIL = "legal_userr_email";
    private static final String COLUMN_LEGAL_USER_PHONE = "legal_user_phone";
    private static final String COLUMN_LEGAL_USER_PASSWORD = "legal_user_password";
    private static final String COLUMN_LEGAL_USER_LOCATION = "worker_location";



    // create worker table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_LEGAL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_LEGAL_USER_NAME + " TEXT,"
            + COLUMN_LEGAL_USER_EMAIL + " TEXT," + COLUMN_LEGAL_USER_PHONE + " TEXT," + COLUMN_LEGAL_USER_PASSWORD + " TEXT,"
            + COLUMN_LEGAL_USER_LOCATION + " TEXT " + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    //constructor
    public LegalUserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }


    //for user record
    public void addLegalUser(LegalUser legalUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEGAL_USER_NAME, legalUser.getName());
        values.put(COLUMN_LEGAL_USER_EMAIL, legalUser.getEmail());
        values.put(COLUMN_LEGAL_USER_PHONE, legalUser.getPhone());
        values.put(COLUMN_LEGAL_USER_PASSWORD, legalUser.getPassword());
        values.put(COLUMN_LEGAL_USER_LOCATION, legalUser.getLocation());



        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    // fetch all user and return the list of user records
    public List<LegalUser> getAllLegalUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_LEGAL_USER_ID,
                COLUMN_LEGAL_USER_EMAIL,
                COLUMN_LEGAL_USER_NAME,
                COLUMN_LEGAL_USER_PASSWORD,
                COLUMN_LEGAL_USER_PHONE,
                COLUMN_LEGAL_USER_LOCATION
        };
        // sorting orders
        String sortOrder =
                COLUMN_LEGAL_USER_NAME + " ASC";
        List<LegalUser> legalUserList = new ArrayList<LegalUser>();
        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LegalUser legalUser = new LegalUser();
                legalUser.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_ID))));
                legalUser.setName(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_NAME)));
                legalUser.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_EMAIL)));
                legalUser.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_PASSWORD)));
                legalUser.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_PHONE)));
                legalUser.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LEGAL_USER_LOCATION)));

                // Adding user record to list
                legalUserList.add(legalUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return worker list
        return legalUserList;
    }

    //update userdata
    public void updateLegalUser(LegalUser legalUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEGAL_USER_NAME, legalUser.getName());
        values.put(COLUMN_LEGAL_USER_EMAIL, legalUser.getEmail());
        values.put(COLUMN_LEGAL_USER_PASSWORD, legalUser.getPassword());
        values.put(COLUMN_LEGAL_USER_PHONE, legalUser.getPhone());
        values.put(COLUMN_LEGAL_USER_LOCATION, legalUser.getLocation());

        // updating row
        db.update(TABLE_USER, values, COLUMN_LEGAL_USER_ID + " = ?",
                new String[]{String.valueOf(legalUser.getId())});
        db.close();
    }

    //delete
    public void deleteLegalUser(LegalUser legalUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_LEGAL_USER_ID + " = ?",
                new String[]{String.valueOf(legalUser.getId())});
        db.close();
    }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEGAL_USER_PASSWORD, password);
        db.update(TABLE_USER,values, COLUMN_LEGAL_USER_EMAIL + " = ?", new String[]{email});
        db.close();

    }


    //check if user exists or not
    public boolean checkLegalUser(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_LEGAL_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_LEGAL_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //check user exist or not
    public boolean checkLegalUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_LEGAL_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_LEGAL_USER_EMAIL + " = ?"  + " AND " + COLUMN_LEGAL_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


}