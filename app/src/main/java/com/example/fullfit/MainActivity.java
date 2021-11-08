package com.example.fullfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void newsBtnClick(View view) {
        Intent intent = new Intent(this,NewsActivity.class);
        startActivity(intent);
    }


    public void measureBtnClick(View view) {
        Intent intent = new Intent(this,MeasureActivity.class);
        startActivity(intent);
    }
}