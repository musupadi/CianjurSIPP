package com.destiny.cianjursipp.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "puskomdik.db";
    private static final int DATABASE_VERSION = 7;
    //Account
    public static final String TABLE_NAME_ACCOUNT = "account";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_PROFILE = "profile";
    public static final String COLUMN_ID_KELAS = "id_kelas";
    public static final String COLUMN_ID = "ID";

    //Eskul
    public static final String TABLE_NAME_ESKUL = "eskul";
    public static final String COLUMN_ESKUL = "column_eskul";
    public static final String ID_ESKUL = "id_eskul";

    //Tugas
    public static final String TABLE_NAME_TUGAS = "tugas";
    public static final String COLUMN_NOMOR = "nomor";
    public static final String COLUMN_JAWABAN = "jawaban";

    //ADS
    public static final String TABLE_NAME_ADS = "ads";
    public static final String COLUMN_COUNT = "count";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_ACCOUNT+" (" +
                COLUMN_USERNAME+" TEXT NOT NULL, "+
                COLUMN_PASSWORD+" TEXT NOT NULL, "+
                COLUMN_NAME+" TEXT NOT NULL, "+
                COLUMN_TOKEN+" TEXT NOT NULL, "+
                COLUMN_LEVEL+" TEXT NOT NULL, "+
                COLUMN_PROFILE+" TEXT NOT NULL, "+
                COLUMN_ID_KELAS+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_NAME_ESKUL+" (" +
                COLUMN_ESKUL+" TEXT NOT NULL, "+
                ID_ESKUL+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_NAME_TUGAS+" (" +
                COLUMN_NOMOR+" TEXT NOT NULL, "+
                COLUMN_JAWABAN+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_NAME_ADS+" (" +
                COLUMN_COUNT+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ESKUL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TUGAS);
        this.onCreate(db);
    }
    //Save
    public void SaveCountADS(String count){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNT, count);
        db.insert(TABLE_NAME_ADS,null,values);
        db.close();
    }
    public void SaveUser(String username,String password,String name,String token,String level,String profile,String id_kelas){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TOKEN, token);
        values.put(COLUMN_LEVEL,level);
        values.put(COLUMN_PROFILE,profile);
        values.put(COLUMN_ID_KELAS,id_kelas);
        db.insert(TABLE_NAME_ACCOUNT,null,values);
        db.close();
    }
    public void SaveEskul(String eskul,String id){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ESKUL, eskul);
        values.put(ID_ESKUL, id);
        db.insert(TABLE_NAME_ESKUL,null,values);
        db.close();
    }
    public void SaveJawaban(String nomor,String Jawaban){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMOR, nomor);
        values.put(COLUMN_JAWABAN, Jawaban);
        db.insert(TABLE_NAME_TUGAS,null,values);
        db.close();
    }
    public void UpdateJawaban(String nomor,String Jawaban){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query ="UPDATE FROM "+TABLE_NAME_TUGAS+" WHERE "+COLUMN_NOMOR+" = "+nomor+" SET "+COLUMN_JAWABAN+" = "+Jawaban;
        db.rawQuery(query,null);
        db.close();
    }
    //CHECKER
    public Cursor checkADS(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ADS;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkEskul(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ESKUL;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkTugas(String nomor){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_TUGAS+" WHERE "+COLUMN_NOMOR+" = "+nomor;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    //delete
    public void Logout(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ACCOUNT+"");
    }
    public void DeleteEskul(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ESKUL+"");
    }
    public void DeleteTugas(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_TUGAS+"");
    }
    public void ResetADS(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ADS+"");
    }
}

