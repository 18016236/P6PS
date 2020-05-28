package com.example.p6ps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTasks;
    Button btnAdd,btnRetrieve;
    ArrayList<Task> al;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = findViewById(R.id.lvTask);
        btnAdd = findViewById(R.id.btnAddTask);

        al = new ArrayList<Task>();
        aa = new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_1,al);
        lvTasks.setAdapter(aa);

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Task data = al.get(position);
               DBHelper dbh = new DBHelper(MainActivity.this);
                Task newTask = null;
                dbh.insertTask(newTask);
                ArrayList<Task>tasks = dbh.getAllNotes();
                ArrayAdapter<Task>adapter = new ArrayAdapter<Task>(
                        MainActivity.this,android.R.layout.simple_list_item_1,tasks);
            this.setListAdapter(adapter);
                
            
            }

            private void setListAdapter(ArrayAdapter<Task> adapter) {
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i,9);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            btnRetrieve.performClick();
        }
    }

}
