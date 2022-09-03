package com.example.parking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;


public class ExitActivity extends AppCompatActivity {
    private EditText plateName_et;
    private Button exitBtn;
    private DBHelper parking;
    private int resultAct_reqCode=1004;
    private String timeStamp;
    private String priceHaveTOBePaid;
    private ImageView back_img;
    private String carPlate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        initial();




            exitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (!carPlate.matches("")) {
                    carPlate = plateName_et.getText().toString();
                        if (parking.theCarIsIn(carPlate)) {
                            parking.insertExitInfo(carPlate, timeStamp);
                            priceHaveTOBePaid = calPrice
                                    (DateDifference.findDifference(
                                            parking.getArrivalDateAndTime(carPlate)
                                            , timeStamp));
                            parking.insertPricePaid(Integer.valueOf(priceHaveTOBePaid), carPlate);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExitActivity.this);
                            alertDialog.setTitle("Exit").setMessage("This " + carPlate + "\n" + "came at " + parking.getArrivalDateAndTime(carPlate) + " \n"+"The cost must be taken: "+priceHaveTOBePaid).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(ExitActivity.this, ExitActivity.class);
                                    startActivity(intent);
                                }
                            });
                            AlertDialog alert = alertDialog.create();
                            alert.setOnShowListener(arg0 -> {
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                            });
                            alert.show();


                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExitActivity.this);
                            alertDialog.setTitle("Error").setMessage("The car is not found!").setCancelable(false).setPositiveButton("Go TO Arrival Panel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(ExitActivity.this, ArrivalActivity.class);
                                    startActivity(intent);
                                }
                            }).setNeutralButton("Try again", null);
                            AlertDialog alert = alertDialog.create();
                            alert.setOnShowListener(arg0 -> {
                                alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.black));
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                            });
                            alert.show();

                        }


//                    }
//                    else{Toast.makeText(ExitActivity.this, "Please fill out the field", Toast.LENGTH_LONG).show();}

                }
            });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ExitActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
//        okBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(ExitActivity.this,ExitActivity.class);
//                startActivity(intent);
////                intent.putExtra("plate",carPlate);
////                startActivityForResult(intent,resultAct_reqCode);
//
//            }
//        });
    }
    private void initial(){
        plateName_et =findViewById(R.id.exit_et_plateName);
        exitBtn =findViewById(R.id.exit_btn_exit);
     //   okBtn=findViewById(R.id.exit_btn_ok);
        parking=new DBHelper(this);
        //cost_txView=findViewById(R.id.exit_textView_cost);
        back_img=findViewById(R.id.exit_back_img);
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }
    public String calPrice(long hour){
        PricePanel pricePanel=parking.getPriceAndHour();
        int fixedPrice=pricePanel.getPrice();
        int fixedHour=pricePanel.getHour();
        long resultPrice=(hour*fixedPrice)/fixedHour;
        return String.valueOf(resultPrice);
    }
}