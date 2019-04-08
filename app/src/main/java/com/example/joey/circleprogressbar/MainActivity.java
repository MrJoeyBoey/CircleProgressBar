package com.example.joey.circleprogressbar;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joey.circleprogressbar.widget.CircleProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressBar circleProgressBar;
    private Button increse,reduce,auto;
    private int currentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        circleProgressBar = (CircleProgressBar)findViewById(R.id.circle_progress);
        increse = (Button)findViewById(R.id.increase_progress);
        reduce = (Button)findViewById(R.id.reduce_progress);
        auto = (Button)findViewById(R.id.automatic_progress);
        currentProgress = 0;
    }

    private void initEvent(){
        increse.setOnClickListener(this);
        reduce.setOnClickListener(this);
        auto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.increase_progress:
                if(currentProgress+10 <= 100){
                    currentProgress += 10;
                }else if(currentProgress+1 <= 100){
                    currentProgress += 1;
                }else {
                    Toast.makeText(this,"无法再继续增加进度",Toast.LENGTH_SHORT).show();
                }
                circleProgressBar.setProgress(currentProgress);
                break;
            case R.id.reduce_progress:
                if(currentProgress-10>=0){
                    currentProgress -= 10;
                }else if(currentProgress-1 >= 0){
                    currentProgress -=1;
                }else {
                    Toast.makeText(this,"无法再继续减少进度",Toast.LENGTH_SHORT).show();
                }
                circleProgressBar.setProgress(currentProgress);
                break;
            case R.id.automatic_progress:
                startAutoIncreaseProgress();
                break;
        }
    }

    private void startAutoIncreaseProgress() {
        currentProgress = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(currentProgress+5 <= 100){
                    currentProgress += 5;
                    circleProgressBar.setProgress(currentProgress);
                    handler.postDelayed(this,1000);
                }else {
                    handler.removeCallbacks(this);
                }

            }
        };
        handler.postDelayed(runnable,1000);
    }

}
