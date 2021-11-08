package com.example.fullfit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fullfit.Models.MeasureModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeasureActivity extends AppCompatActivity {
    String[] heightsArray = new String[]{
            "130-140", "140-150", "150-160", "160-170", "170-180", "180-190", "190-200"
    };

    RadioGroup radioGroup;
    EditText editText;
    Spinner spinner;
    ListView listView;
    ProgressBar progressBar;
    TextView textView;
    ImageButton saveBtn;

    //MeasureModel measureModel = new MeasureModel(30,"150-160","Male");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

        textView = findViewById(R.id.textView4);
        radioGroup = findViewById(R.id.radioGrp);
        ImageButton generateBtn = findViewById(R.id.imageButton);
        editText = findViewById(R.id.weightET);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressBar);
        saveBtn = findViewById(R.id.imageButton2);
        listView = findViewById(R.id.listView2);

        progressBar.setIndeterminate(false);
        textView.setText("");
        listView.setVisibility(View.INVISIBLE);

        List<String> list = new ArrayList<>(Arrays.asList(heightsArray));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.notifyDataSetChanged();
        spinner.setAdapter(arrayAdapter);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            boolean radioChange = false;
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radioChange) {
                    progressBar.setProgress(progressBar.getProgress() + 30);
                    radioChange = true;
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            boolean textChange = false;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!textChange){
                    progressBar.setProgress(progressBar.getProgress() + 30);
                    textChange = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }

    public void calculateBtnClick(View view) throws InterruptedException {

        if (radioGroup.getCheckedRadioButtonId() != -1 && !editText.getText().toString().matches("")) {
            progressBar.setProgress(100);
            listView.setVisibility(View.VISIBLE);

            MeasureModel measureModel = new MeasureModel(Integer.parseInt(String.valueOf(editText.getText())),spinner.getSelectedItem().toString());
            String weightStatus = measureModel.getStatus();
            textView.setText(weightStatus);

            ThreadTask threadTask = new ThreadTask(Integer.parseInt(String.valueOf(editText.getText())),spinner.getSelectedItem().toString());
            Thread thread = new Thread(threadTask);
            thread.start();
            thread.join();
            List<String> adviceList = threadTask.getValues();

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MeasureActivity.this,android.R.layout.simple_list_item_1,adviceList);
            arrayAdapter.notifyDataSetChanged();
            listView.setAdapter(arrayAdapter);
            listView.setVisibility(View.VISIBLE);



        } else {
            Toast errorToast = Toast.makeText(this, "Please fill all the above fields first", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }


    public void saveBtnClick(View view) {
        if (radioGroup.getCheckedRadioButtonId() != -1 && !editText.getText().toString().matches("")) {

            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(id);
            String text = radioButton.getText().toString();

            MeasureModel measureModel = new MeasureModel(Integer.parseInt(String.valueOf(editText.getText())),spinner.getSelectedItem().toString(),text);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String dataString = gson.toJson(measureModel);
            editor.putString("data",dataString);
            editor.commit();
            Toast.makeText(this,"Data has been saved!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast errorToast = Toast.makeText(this, "Please fill all the above fields first", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }


    public void loadBtnClick(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String data = prefs.getString("data","");
        MeasureModel measureModel = gson.fromJson(data,MeasureModel.class);

        System.out.println(measureModel.getWeight()+"...........");
       editText.setText(measureModel.getWeight());

        if(measureModel.getGender().equals("Male")){
            RadioButton radioButton = findViewById(R.id.maleRB);
            radioButton.toggle();
        }
        else {
            RadioButton radioButton = findViewById(R.id.femaleRB);
            radioButton.toggle();
        }

        switch (measureModel.getHeight()){
            case 140:
                spinner.setSelection(0);
                break;
            case 150:
                spinner.setSelection(1);
                break;
            case 160:
                spinner.setSelection(2);
                break;
            case 170:
                spinner.setSelection(3);
                break;
            case 180:
                spinner.setSelection(4);
                break;
            case 190:
                spinner.setSelection(5);
                break;
            default:
                spinner.setSelection(6);
                break;
        }

    }
}