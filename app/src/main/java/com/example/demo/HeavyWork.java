package com.example.demo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;

public class HeavyWork implements Runnable{
    public final static int STATUS_START = 0x001;
    public final static int STATUS_PROGRESS = 0x002;
    public final static int STATUS_END = 0x003;
    public final static String KEY_PROGRESS = "0x004";
    public final static String KEY_RESULT = "0x005";
    static final int COUNT = 9000000;
    private int complexity;
    private Handler messageQueue;
    private int nVal;
    private int currentProgress = 0;

    public HeavyWork(int complexity, Handler messageQueue) {
        this.complexity = complexity;
        this.messageQueue = messageQueue;
    }

    public ArrayList<Double> getArrayNumbers(int n){
        nVal = n;
        ArrayList<Double> returnArray = new ArrayList<>();

        for (int i=0; i<n; i++){
            returnArray.add(getNumber());

        }

        return returnArray;
    }

    public double getNumber(){
        double num = 0;
        int getNumProgress = 0;
        Random ran = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + (Math.random()*ran.nextDouble()*100+ran.nextInt(50))*1000;
            Message progressMessage = new Message();
            progressMessage.what = STATUS_PROGRESS;
            Bundle bundleProgress = new Bundle();
            getNumProgress = i/90000;
            getNumProgress = getNumProgress/nVal;
            bundleProgress.putInt(KEY_PROGRESS, (currentProgress + getNumProgress));
            progressMessage.setData(bundleProgress);
            messageQueue.sendMessage(progressMessage);
        }
        currentProgress = currentProgress + getNumProgress + 1;
        return num / ((double) COUNT);
    }

    @Override
    public void run() {
        ArrayList<Double> arrayList;
        arrayList = getArrayNumbers(complexity);
        Message startMessage = new Message();
        startMessage.what = STATUS_START;
        messageQueue.sendMessage(startMessage);

        double[] arr = new double[3];
        Message endMessage = new Message();
        endMessage.what = STATUS_END;
        Bundle bundleResults = new Bundle();
        arr[0] = findMin(arrayList);
        arr[1] = findMax(arrayList);
        arr[2] = findAvg(arrayList);
        bundleResults.putDoubleArray(KEY_RESULT, arr);
        endMessage.setData(bundleResults);
        messageQueue.sendMessage(endMessage);
    }

    public double findMin(ArrayList<Double> arrayList) {
        double min = arrayList.get(0);
        for(double num: arrayList) {
            System.out.println(num);
            if(num < min) {
                min = num;
            }
        }
        return min;
    }

    public double findMax(ArrayList<Double> arrayList) {
        double max = arrayList.get(0);
        for(double num: arrayList) {
            if(num > max) {
                max = num;
            }
        }
        return max;
    }

    public double findAvg(ArrayList<Double> arrayList) {
        double total = 0;
        double avg;
        for(double num: arrayList) {
            total = total + num;
        }
        avg = total/arrayList.size();
        return avg;
    }

    // public static void main(String[] args) {
//         ArrayList<Double> arrayList = new ArrayList<>();
//         arrayList = getArrayNumbers(200);
//         for(double num: arrayList){
//             System.out.println(num);
    //     }
    // }
}
