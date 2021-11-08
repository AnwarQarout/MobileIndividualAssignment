package com.example.fullfit.Models;

import java.util.ArrayList;
import java.util.List;

public class MeasureModel {
    private int weight;
    private String height;
    private String gender;
    private List<String> advices = new ArrayList<>();

    public MeasureModel(int weight, String height, String gender) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public MeasureModel(int weight, String height) {
        this.weight = weight;
        this.height = height;
    }

    public MeasureModel() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        int realHeight = Integer.parseInt(this.height.substring(4));
        return realHeight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getAdvices() {
        return advices;
    }

    public void setAdvices(List<String> advices) {
        this.advices = advices;
    }

    private double calculateBMI(){
        double realHeight = Integer.parseInt(this.height.substring(4));
        realHeight = realHeight/100;
        System.out.println("--------+++"+realHeight);
        double BMI = (double)this.weight / (double)Math.pow(realHeight,2);
        return BMI;
    }

    public String getStatus(){
        Double bmi = calculateBMI();
        if(bmi < 18.5){
            return "You are UNDERWEIGHT! Here's some advice.";
        }
        else if(bmi >= 18.5 && bmi <= 24.9){
            return "You are NORMAL! Here's some advice.";
        }
        else if(bmi >= 25 && bmi <= 29.9){
            return "You are OVERWEIGHT! Here's some advice.";
        }
        else if(bmi >= 30 && bmi <= 34.9){
            return "You are OBESE CLASS 1! Here's some advice.";
        }
        else if(bmi >= 35 && bmi <= 39.9){
            return "You are OBESE CLASS 2! Here's some advice.";
        }
        return "You are OVERWEIGHT! Here's some advice.";
    }

    public List<String> getAdvice(){
        Double bmi = calculateBMI();
        if(bmi < 18.5){
            advices.add("Don't drink water before meals.");
            advices.add("Eat more often.");
            advices.add("Drink milk.");
            advices.add("Add cream to your coffee.");
            advices.add("Try weight gaining shakes.");
            advices.add("Get quality sleep.");
            return advices;
        }
        else if(bmi >= 18.5 && bmi <= 24.9){
            advices.add("Limit calorie intake and stabilize it.");
            advices.add("Follow a healthy diet.");
            advices.add("Organize your meal times.");
            advices.add("Work out.");
            advices.add("Get quality sleep.");
            return advices;
        }
        else if(bmi >= 25 && bmi <= 29.9){
            advices.add("Drink green tea.");
            advices.add("Follow a healthy weight loss diet.");
            advices.add("Organize your meal times.");
            advices.add("Work out and run on a treadmill.");
            advices.add("Get quality sleep.");
            return advices;
        }

        advices.add("Drink green tea.");
        advices.add("Follow a healthy weight loss diet.");
        advices.add("Organize your meal times.");
        advices.add("Work out and run on a treadmill.");
        advices.add("Get quality sleep.");
        return advices;




    }
}
