package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.defineview.bill.mydefineview.R;
import com.defineview.bill.mydefineview.view.RoundImageView;

import java.util.Random;

/**
 * 圆角图片
 */
public class RoundCornerImageActivity extends AppCompatActivity {

    private RoundImageView mQiQiu;
    private RoundImageView mMeiNv;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_corner_image);

        setTitle("自定义view实现--圆角图片");

        mQiQiu = (RoundImageView) findViewById(R.id.id_qiqiu);
        mMeiNv = (RoundImageView) findViewById(R.id.id_meinv);


        mQiQiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 圆形和圆角切换
                if (type == RoundImageView.TYPE_CIRCLE) {
                    mQiQiu.setType(RoundImageView.TYPE_ROUND);
                    type = 1;
                } else {
                    mQiQiu.setType(RoundImageView.TYPE_CIRCLE);
                    type = 0;
                }

            }
        });

        mMeiNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 随即改变圆角值
                Random random = new Random();
                int i = random.nextInt(10) * 10;

                mMeiNv.setBorderRadius(i);
            }
        });

    }
}
