package com.ibso.student_predict_course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "main.db";

    public DBHelper(Context context) {
        super(context, "main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table userLoginDetails(username TEXT primary key, password TEXT)");
        myDB.execSQL("create Table users(username TEXT primary key, " +
                "fullName TEXT, " +
                "sex TEXT, " +
                "age TEXT, " +
                "address TEXT, " +
                "famsize TEXT, " +
                "Pstatus TEXT, " +
                "Medu TEXT, " +
                "Fedu TEXT, " +
                "Mjob TEXT, " +
                "Fjob TEXT, " +
                "guardian TEXT, " +
                "studytime TEXT, " +
                "failures TEXT, " +
                "activities TEXT, " +
                "internet TEXT, " +
                "famrel TEXT, " +
                "freetime TEXT, " +
                "goout TEXT, " +
                "Dalc TEXT, " +
                "Walc TEXT, " +
                "health TEXT, " +
                "cgpa TEXT, " +
                "psyscore TEXT, " +
                "class TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists userLoginDetails");
        myDB.execSQL("drop Table if exists users");
    }

    public Boolean insertUserRegistrationData(String fullname, String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues userContentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("userLoginDetails", null, contentValues);

        userContentValues.put("username",username);
        userContentValues.put("fullName",fullname);
        userContentValues.putNull("sex");
        userContentValues.putNull("age");
        userContentValues.putNull("address");
        userContentValues.putNull("famsize");
        userContentValues.putNull("Pstatus");
        userContentValues.putNull("Medu");
        userContentValues.putNull("Fedu");
        userContentValues.putNull("Mjob");
        userContentValues.putNull("Fjob");
        userContentValues.putNull("guardian");
        userContentValues.putNull("studytime");
        userContentValues.putNull("failures");
        userContentValues.putNull("activities");
        userContentValues.putNull("internet");
        userContentValues.putNull("famrel");
        userContentValues.putNull("freetime");
        userContentValues.putNull("goout");
        userContentValues.putNull("Dalc");
        userContentValues.putNull("Walc");
        userContentValues.putNull("health");
        userContentValues.putNull("cgpa");
        userContentValues.putNull("psyscore");
        userContentValues.putNull("class");
        long result2 = myDB.insert("users", null, userContentValues);
        if(result==-1 && result2==-1) return false;
        else
            return true;

    }

    public Boolean checkUserName(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from userLoginDetails where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserNamePassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from userLoginDetails where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
