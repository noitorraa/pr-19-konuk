package com.example.pr_19_konuk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;
import com.example.pr_19_konuk.R;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView timePick;
    Button btnTime, btnDate;
    Calendar dateAndTime = Calendar.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePick = findViewById(R.id.time_pick);
        btnDate = findViewById(R.id.button_date);
        btnTime = findViewById(R.id.button_time);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        setInitialDateTime();
    }
    private void setInitialDateTime() {
        timePick.setText(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.button_date:
            new DatePickerDialog(MainActivity.this, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH))
                    .show();
            break;
        case R.id.button_time:
            new TimePickerDialog(MainActivity.this, t, dateAndTime.get(Calendar.HOUR_OF_DAY),
                    dateAndTime.get(Calendar.MINUTE), true)
                    .show();
            break;
    }
    }
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
    DatePickerDialog.OnDateSetListener d =new DatePickerDialog.OnDateSetListener() {
        public  void onDateSet(DatePicker view, int year, int mounthOfYear, int dayOfMounth){
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, mounthOfYear);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMounth);
        setInitialDateTime();
        }
    };
}