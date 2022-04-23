package com.z.doctor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button addWord,viewWords,translate;
    RadioButton radioButton;
    AlertDialog.Builder alert;



    public void aboutMeDialog() {
        alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("ABOUT ME");
        alert.setMessage("Hello my name is johny fade keriake and this is my second project i made it in 20/4/2022");
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alert.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

         addWord=findViewById(R.id.button2);
         viewWords=findViewById(R.id.button1);
         translate=findViewById(R.id.button3);
         radioButton=findViewById(R.id.aboutme);



         addWord.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent TheWordActivity=new Intent(MainActivity.this,TheWord.class);
                 startActivity(TheWordActivity);
             }
         });
         viewWords.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent DataActivity=new Intent(MainActivity.this,Data.class);
                 startActivity(DataActivity);
             }
         });
         translate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent TranslateActivity=new Intent(MainActivity.this,translate.class);
                 startActivity(TranslateActivity);
             }
         });

         radioButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 aboutMeDialog();
             }
         });

    }
}