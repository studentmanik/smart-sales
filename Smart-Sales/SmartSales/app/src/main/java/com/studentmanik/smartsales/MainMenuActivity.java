package com.studentmanik.smartsales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.studentmanik.smartsales.SyncAdapter.SyncUtils;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ImageView ivRoute= (ImageView) findViewById(R.id.ivRoute);
        ImageView ivLogout= (ImageView) findViewById(R.id.ivLogout);
        ImageView ivSync= (ImageView) findViewById(R.id.ivSync);
        ImageView ivVrefy= (ImageView) findViewById(R.id.ivVrefy);
        ivVrefy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),OutletCreateActivity.class);
                startActivity(intent);
            }
        });
        ivRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),OutletListActivity.class);
                startActivity(intent);
            }
        });

        ivSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SyncUtils.TriggerRefresh();
            }
        });
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("userSession", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("srId", 0);
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
