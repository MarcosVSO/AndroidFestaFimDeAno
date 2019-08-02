package com.example.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SecurityPreferences {
    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno",Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key,value).apply();
        System.out.println(value);
    }

    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key,"");
    }
}
