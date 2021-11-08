package com.example.fullfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fullfit.Models.NewsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class NewsActivity extends AppCompatActivity {

    NewsModel newsModel = new NewsModel("title","Description");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ListView listView = findViewById(R.id.listView);
        final List<NewsModel> elementsList = new ArrayList<>();
        elementsList.add(newsModel);
        final ArrayAdapter<NewsModel> adapter = new ArrayAdapter<>(NewsActivity.this,android.R.layout.simple_list_item_1,elementsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               NewsModel o = (NewsModel) adapterView.getItemAtPosition(i);
                System.out.println(o.getNewsBody());
               Intent intent = new Intent(NewsActivity.this,NewsDescriptionActivity.class);
               intent.putExtra("description",o.getNewsBody());
               startActivity(intent);
            }
        });

    }


}