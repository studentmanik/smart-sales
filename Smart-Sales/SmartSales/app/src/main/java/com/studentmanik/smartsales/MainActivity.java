package com.studentmanik.smartsales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences= getSharedPreferences("userSession", Context.MODE_PRIVATE);
        int srId = sharedpreferences.getInt("srId", 0);
        if (srId!=0){
            Intent intent=new Intent(getApplicationContext(),MainMenuActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }

    }
}
