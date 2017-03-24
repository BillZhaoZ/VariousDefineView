package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;

/**
 * 自定义imageview界面
 */
public class CustomImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_image_view);
        setTitle("自定义View (二) 进阶");
    }
}
