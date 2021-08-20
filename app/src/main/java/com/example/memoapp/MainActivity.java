package com.example.memoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
    SQLiteDatabase sd;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd=openOrCreateDatabase("memo.db",0,null);

        String schema = "create table note (_id integer primary key autoincrement, title text not null, body text not null);";
        try {
            sd.execSQL(schema);
        }catch (Exception ignore){
            Log.d("%%%%%%%%","db error");
        }
        ContentValues values = new ContentValues();
        values.put("title","SS");
        values.put("body","ㅎㅇ");
        sd.insert("note",null,values);
        setContentView(R.layout.mainactivity);
        ListView list = findViewById(R.id.list);
        c = sd.query("note",null,null,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.line,c,new String[]{"_id","title"},new int[]{R.id._id,R.id.title});
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }
}