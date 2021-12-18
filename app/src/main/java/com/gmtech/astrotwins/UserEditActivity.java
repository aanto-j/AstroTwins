package com.gmtech.astrotwins;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        ShapeableImageView man = findViewById(R.id.man);
        ShapeableImageView woman  = findViewById(R.id.woman);
        EditText datetext = findViewById(R.id.date);
        EditText timetext = findViewById(R.id.time);
        final Calendar myCalendar = Calendar.getInstance();
        final Calendar myTime = Calendar.getInstance();
        Dialog okay = new Dialog(UserEditActivity.this);
        Button update_prof = findViewById(R.id.update_profile);
        ImageButton exit = findViewById(R.id.exit);

        Window window = okay.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        okay.setContentView(R.layout.profile_update_layout);
        update_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okay.show();
            }
        });

        Button ok = okay.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okay.dismiss();
                finish();
            }
        });

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                myTime.set(Calendar.HOUR,hours);
                myTime.set(Calendar.MINUTE,minutes);
                updateTime(timetext,myTime);
            }
        };

        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(UserEditActivity.this,time,myTime.get(Calendar.HOUR),myTime.get(Calendar.MINUTE),false).show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(datetext,myCalendar);
            }

        };

        datetext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserEditActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                man.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.EndColor)));
                man.setStrokeWidth(5);
                woman.setStrokeWidth(0);
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                woman.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.EndColor)));
                woman.setStrokeWidth(5);
                man.setStrokeWidth(0);
            }
        });
    }
    private void updateLabel(EditText edittext,Calendar myCalendar) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateTime(EditText edittext,Calendar myCalendar) {
        String myFormat = "HH/mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}