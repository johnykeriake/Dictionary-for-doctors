package com.z.doctor;


import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import model.OneWord;

public class Data extends AppCompatActivity implements RecyclerViewClickInterface{
    List<OneWord> allWords = new ArrayList<>();
    DataBaseHelper dataBaseHelper;
    OneWord oneWord;
    AlertDialog.Builder alert;
    RecyclerView recyclerView;
    MyAdapter myAdapter;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuesearch,menu);

        menu.findItem(R.id.search);
        SearchView searchView= (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<OneWord>FilteredList=new ArrayList<>();
                for (OneWord p : allWords) {
                    if(p.getEnglish_word().toLowerCase().contains(s.toLowerCase())){
                        FilteredList.add(p);
                    }
                }
                myAdapter.filterList(FilteredList);
                return false;
            }
        });

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                deleteDialog();
                return true;
            case R.id.edit:
                edit();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }


    public void deleteDialog() {
        alert = new AlertDialog.Builder(Data.this);
        alert.setTitle("Confirm Delete");
        alert.setMessage("do you want to delete this word:");
        alert.setCancelable(false);
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBaseHelper.deleteWord(oneWord);
                Data.this.recreate();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        alert.show();
    }


    public void edit() {
        alert = new AlertDialog.Builder(Data.this);
        alert.setCancelable(false);
        LinearLayout layout = new LinearLayout(Data.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView textViewEnglish=new TextView(Data.this);
        TextView textViewArabic=new TextView(Data.this);
        EditText englishEditText = new EditText(Data.this);
        EditText arabicEditText = new EditText(Data.this);
        englishEditText.setText(oneWord.getEnglish_word());
        arabicEditText.setText(oneWord.getArabic_word());
        textViewEnglish.setText("Edit the english word:");
        textViewEnglish.setTextSize(20);
        textViewEnglish.setTextColor(getResources().getColor(R.color.black));
        textViewArabic.setText("Edit the arabic word:");
        textViewArabic.setTextSize(20);
        textViewArabic.setTextColor(getResources().getColor(R.color.black));
        layout.addView(textViewEnglish);
        layout.addView(englishEditText);
        layout.addView(textViewArabic);
        layout.addView(arabicEditText);

        alert.setView(layout);

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBaseHelper.updateWord(new OneWord(oneWord.getId(),arabicEditText.getText().toString(),englishEditText.getText().toString()));
                Data.this.recreate();
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
        setContentView(R.layout.activity_data);


        dataBaseHelper = new DataBaseHelper(Data.this);
        allWords = dataBaseHelper.getAllWords();
        recyclerView = findViewById(R.id.recycler);
        myAdapter = new MyAdapter(Data.this, allWords,this);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);


    }


    @Override
    public void OnLongItemLongClick(OneWord one) {
        oneWord=one;
        registerForContextMenu(recyclerView);
    }

}