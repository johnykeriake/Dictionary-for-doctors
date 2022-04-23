package model;

public class OneWord {

    public  String arabic_word ;
    public  String english_word ;
    public  int id ;


    public OneWord(int id,String arabic_word, String english_word ) {
        this.arabic_word = arabic_word;
        this.english_word = english_word;
        this.id = id;
    }
    public OneWord(String arabic_word, String english_word ) {
        this.arabic_word = arabic_word;
        this.english_word = english_word;

    }
    public OneWord() {
    }

    public String getArabic_word() {
        return arabic_word;
    }

    public void setArabic_word(String arabic_word) {
        this.arabic_word = arabic_word;
    }

    public String getEnglish_word() {
        return english_word;
    }

    public void setEnglish_word(String english_word) {
        this.english_word = english_word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OneWord{" +
                "arabic_word='" + arabic_word + '\'' +
                ", english_word='" + english_word + '\'' +
                ", id=" + id +
                '}';
    }
}
