package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
            private Button pricePanel_btn;
            private Button arrival_btn;
            private Button exit_btn;
            private int pricePanel_reqCode=1001;
            private int arrival_reqCode=1002;
            private int exit_reqCode=1003;
            public static final String TAG="MainActivity";
            private DBHelper parking;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                initialID();

                //Log.i(TAG,"onCreate: "+pricePanel1.getPrice()+" for "+pricePanel1.getHour()+" hours");



                pricePanel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(com.example.parking.MainActivity.this,PricePanelActivity.class);
                        startActivityForResult(intent,pricePanel_reqCode);
                    }
                });
                arrival_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(com.example.parking.MainActivity.this,ArrivalActivity.class);
                        startActivityForResult(intent,arrival_reqCode);
                    }
                });
                exit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(com.example.parking.MainActivity.this,ExitActivity.class);
                        startActivityForResult(intent,exit_reqCode);
                    }
                });







            }
            private void initialID(){
                pricePanel_btn=findViewById(R.id.btn_main_pricePanel);
                arrival_btn=findViewById(R.id.btn_main_arrival);
                exit_btn=findViewById(R.id.btn_main_exit);
                parking=new DBHelper(this);
            }
        }

