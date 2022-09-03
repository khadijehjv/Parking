package com.example.parking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class ArrivalActivity extends AppCompatActivity {
    private EditText plateName_et;
    private Button saveBtn;
    private DBHelper parking;
    private String timeStamp;
    private ImageView back_img;
    private String carPlate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
        initial();

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (!carPlate.matches("")){
                    carPlate = plateName_et.getText().toString();
                        if (parking.theCarIsIn(carPlate)) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ArrivalActivity.this, R.style.MyDialogTheme);
                            alertDialog.setTitle("Error").setMessage("The Car is in!").setCancelable(false).setPositiveButton("Go To Exit Panel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    Intent intent = new Intent(ArrivalActivity.this, ExitActivity.class);
                                    startActivity(intent);
                                }
                            }).setNeutralButton("Try again", null);
                            AlertDialog alert = alertDialog.create();
                            alert.setOnShowListener(arg0 -> {
                                alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.black));
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                            });
                            alert.show();
                        } else {


                            parking.insertArrivalInfo(carPlate, "2022.09.03.07.00.00");
                            Toast.makeText(ArrivalActivity.this, "The car is registered", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ArrivalActivity.this, ArrivalActivity.class);
                            startActivity(intent);
                        }
//                }else{Toast.makeText(ArrivalActivity.this, "Please fill out the field", Toast.LENGTH_LONG).show();}
                }
            });


        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ArrivalActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initial(){
        plateName_et =findViewById(R.id.arrival_et_plateName);

        saveBtn=findViewById(R.id.arrival_btn_save);

        parking=new DBHelper(this);
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        back_img=findViewById(R.id.arrival_back_img);

    }
}