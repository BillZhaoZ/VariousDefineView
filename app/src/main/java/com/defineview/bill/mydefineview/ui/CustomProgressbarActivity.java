package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;
import com.defineview.bill.mydefineview.view.CustomProgressBar;

/**
 * 自定义进度条
 */
public class CustomProgressbarActivity extends AppCompatActivity {

    private CustomProgressBar customProgressBar01;
    private CustomProgressBar customProgressBar02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_ring);
        setTitle("自定义View (三) 圆环交替 等待效果");

        customProgressBar01 = (CustomProgressBar) findViewById(R.id.customProgressBar);
        customProgressBar02 = (CustomProgressBar) findViewById(R.id.customProgressBar2);
    }

    @Override
    protected void onStop() {
        super.onStop();

        customProgressBar01.setContinue(false);
        customProgressBar02.setContinue(false);
    }
}
