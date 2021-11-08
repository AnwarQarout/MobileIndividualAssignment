package com.example.fullfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);
        TextView textView = findViewById(R.id.descriptionID);
        String description = getIntent().getStringExtra("description");
        System.out.println(description);
        textView.setText(description);

    }
}