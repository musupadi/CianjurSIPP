package com.destiny.cianjursipp.Model.Alquran.Universal;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class QuranModel {
    @SerializedName("number")
    @Nullable
    public String number;

    @SerializedName("name")
    @Nullable
    public String name;

    @SerializedName("name_latin")
    @Nullable
    public String name_latin;

    @SerializedName("number_of_ayah")
    @Nullable
    public String number_of_ayah;

    @SerializedName("text")
    @Nullable
    public TextModel text;

    @SerializedName("translations")
    @Nullable
    public TranslationModel translations;


    @Nullable
    public String getNumber() {
        return number;
    }

    public void setNumber(@Nullable String number) {
        this.number = number;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getName_latin() {
        return name_latin;
    }

    public void setName_latin(@Nullable String name_latin) {
        this.name_latin = name_latin;
    }

    @Nullable
    public String getNumber_of_ayah() {
        return number_of_ayah;
    }

    public void setNumber_of_ayah(@Nullable String number_of_ayah) {
        this.number_of_ayah = number_of_ayah;
    }

    @Nullable
    public TextModel getText() {
        return text;
    }

    public void setText(@Nullable TextModel text) {
        this.text = text;
    }

    @Nullable
    public TranslationModel getTranslations() {
        return translations;
    }

    public void setTranslations(@Nullable TranslationModel translations) {
        this.translations = translations;
    }
}

