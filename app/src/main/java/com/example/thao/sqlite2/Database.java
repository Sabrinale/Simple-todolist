package com.example.thao.sqlite2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thao on 12-Jun-17.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // truy van khong ta ket qua CREATE INSERT DELATE UPDATE
    public void QueryData (String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // TRUY VAN TRA KET QUA LA : SELECT
    public Cursor GetData (String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
