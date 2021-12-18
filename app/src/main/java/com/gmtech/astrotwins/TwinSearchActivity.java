package com.gmtech.astrotwins;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TwinSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twin_search);
        final Calendar myCalendar = Calendar.getInstance();
        final Calendar myTime = Calendar.getInstance();

        Button button = findViewById(R.id.search);
        EditText datetext = findViewById(R.id.date);
        EditText timetext = findViewById(R.id.time);
        EditText placetext = findViewById(R.id.place);
        String[] eText = getResources().getStringArray(R.array.places);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,eText);
        ProgressDialog progressDialog = new ProgressDialog(TwinSearchActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Astro twins are two people born on same day,month,year,time and place.");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        for(int i=1;i<=2;i++){
                            try{
                                Thread.sleep(1000);
                                progressDialog.incrementProgressBy((int)(100/5));
                            }
                            catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        progressDialog.dismiss();
                        Intent intent = new Intent(TwinSearchActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }

                }).start();
            }
        });

        Dialog dialog = new Dialog(TwinSearchActivity.this);
        dialog.setContentView(R.layout.select_place_layout);

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        placetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        ImageButton exit = (ImageButton) dialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ListView listView;
        listView = dialog.findViewById(R.id.place);
        EditText placename = dialog.findViewById(R.id.placetext);

        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(TwinSearchActivity.this,android.R.layout.simple_list_item_1 ,eText );
        listView.setAdapter(arrayAdapter);
        listView.setTextFilterEnabled(true);

        placename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrayAdapter.getFilter().filter(editable);
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
                new TimePickerDialog(TwinSearchActivity.this,time,myTime.get(Calendar.HOUR),myTime.get(Calendar.MINUTE),false).show();
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
                new DatePickerDialog(TwinSearchActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                placetext.setText(eText[(int)l]);
                placename.setText(eText[(int)l]);
            }
        });

        Button ok = dialog.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void setProgressValue() {

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