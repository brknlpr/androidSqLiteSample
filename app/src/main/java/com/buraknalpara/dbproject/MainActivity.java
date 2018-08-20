package com.buraknalpara.dbproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase dbStudent = this.openOrCreateDatabase("Students", MODE_PRIVATE, null);

        try {
            CreateTable(dbStudent);
            //AddData(dbStudent);
            //GetData(dbStudent);

            UpdateData(dbStudent);
            GetData(dbStudent);

            DeleteData(dbStudent);
            GetData(dbStudent);
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }

    }


    public void CreateTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS students(name VARCHAR, age INT(2))");
        Log.i("SqLite", "--------------------");
        Log.i("SqLite", "students table Created");
        Log.i("SqLite", "--------------------");
    }

    public void AddData(SQLiteDatabase db){
        db.execSQL("INSERT INTO students (name,age) VALUES ('Burak', 25)");
        db.execSQL("INSERT INTO students (name,age) VALUES ('Buraknalpara', 35)");
        db.execSQL("INSERT INTO students (name,age) VALUES ('Nalpara', 45)");
        Log.i("SqLite", "--------------------");
        Log.i("SqLite", "students added");
        Log.i("SqLite", "--------------------");
    }

    public void UpdateData(SQLiteDatabase db){
        db.execSQL("Update students Set age = 30 where name  = 'Nalpara'");
        Log.i("SqLite", "--------------------");
        Log.i("SqLite", "student data updated");
        Log.i("SqLite", "--------------------");
    }

    public void GetData(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("Select * from students",null);

        int nameIx = cursor.getColumnIndex("name");
        int ageIx = cursor.getColumnIndex("age");

        cursor.moveToFirst();

        while (cursor != null){
            Log.i("SqLite", "--------------------");
            Log.i("SqLite", "Get students called");
            Log.i("SqLite", "--------------------");
            Log.i("SqLite", cursor.getString(nameIx));
            Log.i("SqLite", Integer.toString(cursor.getInt(ageIx)));
            cursor.moveToNext();
        }

    }

    public void DeleteData(SQLiteDatabase db){
        db.execSQL("DELETE from students where name like 'Bur%'");
        Log.i("SqLite", "--------------------");
        Log.i("SqLite", "Student Deleted");
        Log.i("SqLite", "--------------------");
    }
}
