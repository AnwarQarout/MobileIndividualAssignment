package com.example.fullfit;

import com.example.fullfit.Models.MeasureModel;

import java.util.List;

public class ThreadTask implements Runnable {
    private volatile MeasureModel measureModel;
    public ThreadTask(int weight, String height){
        measureModel = new MeasureModel(weight,height);
    }
    @Override
    public void run() {
    }

    public List<String> getValues(){
        return measureModel.getAdvice();
    }
}
