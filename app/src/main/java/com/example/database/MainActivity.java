package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Polubenskiy Lab06 - Database SQLite

    EditText txt_key;   // text boxes
    EditText txt_value;
    Database myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_key = findViewById(R.id.txt_key);   // acquire text boxes
        txt_value = findViewById(R.id.txt_value);

        // create new or open database file "mybase.db" with version 1
        myDatabase = new Database(this, "mybase.db", null, 1);
    }

    public void on_insert_Click(View v)
    {
        String key = txt_key.getText().toString();  // get key and value strings
        String value = txt_value.getText().toString();

        myDatabase.do_insert(key, value);   // insert into table
    }

    public void on_select_Click(View v)
    {
        String key = txt_key.getText().toString();  // get key string
        String value = myDatabase.do_select(key);   // find value for that key in table

        txt_value.setText(value);   //show results
    }

    public void on_update_Click(View v)
    {
        String key = txt_key.getText().toString();
        String value = txt_value.getText().toString();

        txt_value.setText(myDatabase.do_update(key,value));
    }

    public void on_delete_Click(View v)
    {
        String key = txt_key.getText().toString();

        txt_value.setText(myDatabase.do_delete(key));
    }
}