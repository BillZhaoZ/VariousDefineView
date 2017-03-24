package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;

public class CustomAudioControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_audio_control);
        setTitle("自定义View (四) 视频音量调节控制");

    }
}
