package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class ExitActivity extends AppCompatActivity {
    private EditText plateName_et;
    private EditText year_et;
    private EditText month_et;
    private EditText day_et;
    private EditText hour_et;
    private EditText minute_et;
    private Button saveBtn;
    private Button cancelBtn;
    private DBHelper parking;
    private int resultAct_reqCode=1004;
    private String timeStamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        initial();




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carPlate = plateName_et.getText().toString();
//                String year = year_et.getText().toString();
//                String month = minute_et.getText().toString();
//                String day = day_et.getText().toString();
//                String hour = hour_et.getText().toString();
//                String min = minute_et.getText().toString();
//                year+"."+month+"."+day+"."+hour+"."+min+"."+"00"
                parking.insertExitInfo(carPlate,timeStamp);

                Intent intent=new Intent(ExitActivity.this,ResultActivity.class);
                intent.putExtra("plate",carPlate);
                startActivityForResult(intent,resultAct_reqCode);



                setResult(RESULT_OK);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private void initial(){
        plateName_et =findViewById(R.id.exit_et_plateName);
        year_et=findViewById(R.id.exit_et_year);
        month_et =findViewById(R.id.exit_et_month);
        day_et =findViewById(R.id.exit_et_day);
        hour_et =findViewById(R.id.exit_et_hour);
        minute_et=findViewById(R.id.exit_et_minute);
        saveBtn=findViewById(R.id.exit_btn_save);
        cancelBtn=findViewById(R.id.exit_btn_cancel);
        parking=new DBHelper(this);
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        year_et.setText(MyDate.getYear(timeStamp), TextView.BufferType.EDITABLE);
        month_et.setText(MyDate.getMonth(timeStamp), TextView.BufferType.EDITABLE);
        day_et.setText(MyDate.getDay(timeStamp), TextView.BufferType.EDITABLE);
        hour_et.setText(MyDate.getHour(timeStamp), TextView.BufferType.EDITABLE);
        minute_et.setText(MyDate.getMin(timeStamp), TextView.BufferType.EDITABLE);

    }
}