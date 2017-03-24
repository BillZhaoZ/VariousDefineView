package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;

/**
 * 自定义随机数
 */
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_view);
        setTitle("自定义View (一) 随机数");
    }
}
