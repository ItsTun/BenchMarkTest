package com.example.tunhanmyae.benchmarktest.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.tunhanmyae.benchmarktest.model.Speed;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VER = 1;

    public Database(Context context){
        super(context, DB_NAME,null, DB_VER);
    }


    public List<Speed> getSpeedwithDate(String date){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"upload", "download", "ping","date"};
        String sqlTable = "bench";
        qb.setTables(sqlTable);

        Cursor c = qb.query(db,sqlSelect, "date='"+date+"'", null, null, null, null);



        final List<Speed> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Speed(c.getDouble(c.getColumnIndex("upload")),
                        c.getDouble(c.getColumnIndex("download")),

                        c.getDouble(c.getColumnIndex("ping")),
                        c.getString(c.getColumnIndex("date"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }

    public void save(Speed speed){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO bench(upload, download, ping, date) VALUES('%s','%s','%s','%s');",
                speed.getUpload(),
                speed.getDownload(),
                speed.getPing(),
                speed.getDate());

        db.execSQL(query);
    }

}
