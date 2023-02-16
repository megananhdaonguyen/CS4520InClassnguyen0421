package com.example.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Megan Nguyen
// InClass04
public class InClass04 extends AppCompatActivity {

    private ExecutorService threadPool;
    private TextView complexity;
    private SeekBar seekbar;
    private TextView min_Result;
    private TextView max_Result;
    private TextView avg_Result;
    private Button button_Generate_Num;
    private ProgressBar progressBar;
    private int seekbar_val = 0;
    private Handler messageQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_generator);
        setTitle("Number Generator");
        threadPool = Executors.newFixedThreadPool(1);

        complexity = findViewById(R.id.textview_complexity);
        seekbar = findViewById(R.id.seekbar_complexity);
        min_Result = findViewById(R.id.textview_min_result);
        max_Result = findViewById(R.id.textview_max_result);
        avg_Result = findViewById(R.id.textview_average_result);
        button_Generate_Num = findViewById(R.id.button_generate);
        progressBar = findViewById(R.id.progressBar_generate_number);

        seekbar_val = 3;
        complexity.setText("3 times");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_val = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seek) {
                complexity.setText(String.format("%d times", seekbar_val));
            }
        });

        messageQueue = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch(message.what){
                    case HeavyWork.STATUS_START:
                        break;
                    case HeavyWork.STATUS_END:
                        Bundle receivedDataEnd = message.getData();
                        double[] results = receivedDataEnd.getDoubleArray(HeavyWork.KEY_RESULT);
                        min_Result.setText(String.format("%s", results[0]));
                        max_Result.setText(String.format("%s", results[1]));
                        avg_Result.setText(String.format("%s", results[2]));
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        break;
                    case HeavyWork.STATUS_PROGRESS:
                        Bundle receivedDataProgress = message.getData();
                        int progress = receivedDataProgress.getInt(HeavyWork.KEY_PROGRESS);
                        progressBar.setProgress(progress + 1);
                        break;
                }
                return false;
            }
        });

        button_Generate_Num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seekbar_val == 0) {
                    min_Result.setText("0");
                    max_Result.setText("0");
                    avg_Result.setText("0");
                } else {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    threadPool.execute(
                            new HeavyWork(seekbar_val, messageQueue)
                    );
                }
            }
        });

    }
}
