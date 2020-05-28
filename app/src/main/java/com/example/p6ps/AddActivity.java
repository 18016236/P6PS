package com.example.p6ps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Button btnAdd,btnCancel;
    TextView tvName,tvDesc;
    EditText etName,etDesc;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = findViewById(R.id.btnAddTasks);
        tvDesc = findViewById(R.id.tvDesc);
        tvName = findViewById(R.id.tvName);
        etDesc = findViewById(R.id.etDesc);
        etName = findViewById(R.id.etName);

        Intent i = getIntent();
        data = (Task) i.getSerializableExtra("data");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(AddActivity.this,
                        PSBroadcastReceiver.class);


                int reqCode = 0;
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, 0,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });
    }

}

