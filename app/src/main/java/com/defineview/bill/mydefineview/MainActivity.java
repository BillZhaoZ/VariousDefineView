package com.defineview.bill.mydefineview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.defineview.bill.mydefineview.ui.CustomAudioControlActivity;
import com.defineview.bill.mydefineview.ui.CustomImageViewActivity;
import com.defineview.bill.mydefineview.ui.CustomImgContainerActivity;
import com.defineview.bill.mydefineview.ui.CustomProgressbarActivity;
import com.defineview.bill.mydefineview.ui.CustomViewActivity;
import com.defineview.bill.mydefineview.ui.FlowLayoutActivity;
import com.defineview.bill.mydefineview.ui.NBProgressBarActivity;
import com.defineview.bill.mydefineview.ui.RoundCornerImageActivity;
import com.defineview.bill.mydefineview.ui.VDHDeepLayoutActivity;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button view1 = (Button) findViewById(R.id.bt_custom_view_01);
        Button view2 = (Button) findViewById(R.id.bt_custom_view_02);
        Button view3 = (Button) findViewById(R.id.bt_custom_view_03);
        Button view4 = (Button) findViewById(R.id.bt_custom_view_04);
        Button view5 = (Button) findViewById(R.id.bt_custom_view_05);
        Button view6 = (Button) findViewById(R.id.bt_custom_view_06);
        Button view7 = (Button) findViewById(R.id.bt_custom_view_07);
        Button view8 = (Button) findViewById(R.id.bt_custom_view_08);
        Button view9 = (Button) findViewById(R.id.bt_custom_view_09);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
        view5.setOnClickListener(this);
        view6.setOnClickListener(this);
        view7.setOnClickListener(this);
        view8.setOnClickListener(this);
        view9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_custom_view_01:
                startActivity(new Intent(view.getContext(), CustomViewActivity.class));
                break;

            case R.id.bt_custom_view_02:
                startActivity(new Intent(view.getContext(), CustomImageViewActivity.class));
                break;

            case R.id.bt_custom_view_03:
                startActivity(new Intent(view.getContext(), CustomProgressbarActivity.class));
                break;

            case R.id.bt_custom_view_04:
                startActivity(new Intent(view.getContext(), CustomAudioControlActivity.class));
                break;

            case R.id.bt_custom_view_05:
                startActivity(new Intent(view.getContext(), CustomImgContainerActivity.class));
                break;

            case R.id.bt_custom_view_06:
                startActivity(new Intent(view.getContext(), VDHDeepLayoutActivity.class));
                break;

            case R.id.bt_custom_view_07:
                startActivity(new Intent(view.getContext(), FlowLayoutActivity.class));
                break;

            case R.id.bt_custom_view_08:
                startActivity(new Intent(view.getContext(), RoundCornerImageActivity.class));
                break;

            case R.id.bt_custom_view_09:
                startActivity(new Intent(view.getContext(), NBProgressBarActivity.class));
                break;
        }
    }
}
