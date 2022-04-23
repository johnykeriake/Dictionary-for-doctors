package com.z.doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import model.OneWord;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, Utils.key_database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTable=" CREATE TABLE "+ Utils.key_table_name +"("+Utils.key_id+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ Utils.key_arabic+" TEXT , "+ Utils.key_english+" TEXT "+")" ;
        sqLiteDatabase.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.key_table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean AddWord(OneWord oneWord) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.key_arabic, oneWord.getArabic_word());
        contentValues.put(Utils.key_english, oneWord.getEnglish_word());
        long insert = sqLiteDatabase.insert(Utils.key_table_name, null, contentValues);
        return insert != -1;
    }

    public List<OneWord> getAllWords() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<OneWord> ListOfWords = new ArrayList<OneWord>();
        String getAll = " SELECT * FROM " + Utils.key_table_name;
        Cursor cursor = sqLiteDatabase.query(Utils.key_table_name, new String[] { Utils.key_id,
                        Utils.key_arabic,Utils.key_english }, null, null,
                null, null, Utils.key_english+ " Collate NOCASE ");
        //ASC


        if (cursor.moveToFirst()) {
            do {
                OneWord oneWord = new OneWord();
                oneWord.setId(cursor.getInt(0));
                oneWord.setArabic_word(cursor.getString(1));
                oneWord.setEnglish_word(cursor.getString(2));
                ListOfWords.add(oneWord);


            } while (cursor.moveToNext());
        }
            cursor.close();
            sqLiteDatabase.close();

            return ListOfWords;


    }


    public boolean deleteWord(OneWord oneWord){
        SQLiteDatabase database=this.getWritableDatabase();
        String deleteItem=" DELETE FROM "+ Utils.key_table_name +" WHERE "+ Utils.key_id+"="+oneWord.getId();
        Cursor cursor= database.rawQuery(deleteItem,null);
        if(cursor.moveToFirst()){
            return  true;
        }
        else{
            return false;
        }

    }

    public boolean updateWord(OneWord oneWord) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.key_arabic, oneWord.getArabic_word());
        contentValues.put(Utils.key_english, oneWord.getEnglish_word());
        long insert = sqLiteDatabase.update(Utils.key_table_name,contentValues, "id=?",new String[]{String.valueOf(oneWord.getId())});
        return insert != -1;
    }


}
