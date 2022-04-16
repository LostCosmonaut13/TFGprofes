package com.example.tfgprofes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.*;
import android.database.sqlite.SQLiteOpenHelper;


class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumno(id integer primary key autoincrement, telefono text, nombre text, email text, telPersonaContacto text, precioHora text, total text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists alumno");
        db.execSQL("create table alumno(id integer primary key autoincrement, telefono text, nombre text, email text, telPersonaContacto text, precioHora text, total text)");
    }
}



