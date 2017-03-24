package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;

public class CustomImgContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_img_container);
        setTitle("手把手教您自定义ViewGroup（一）");
    }
}
