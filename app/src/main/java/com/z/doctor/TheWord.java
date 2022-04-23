package com.z.doctor;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import model.OneWord;

public class TheWord extends AppCompatActivity {
    Button ok;
    EditText arabic, english;
    DataBaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_the_word);
        ok = findViewById(R.id.button);
        arabic = findViewById(R.id.editTextTextPersonName);
        english = findViewById(R.id.editTextTextPersonName2);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (english.getText().toString().equals("") || arabic.getText().equals("")) {
                    Toast.makeText(TheWord.this, "you didn't put anything!", Toast.LENGTH_LONG).show();

                } else {
                    OneWord oneWord;
                    oneWord = new OneWord(arabic.getText().toString(), english.getText().toString());
                    databaseHelper = new DataBaseHelper(TheWord.this);
                    databaseHelper.AddWord(oneWord);
                    Toast.makeText(TheWord.this, "word has been added", Toast.LENGTH_SHORT).show();
                    english.setText(null);
                    arabic.setText(null);
                }
            }
        });
    }
}