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

        intiView();
    }

    /**
     * 初始化
     */
    private void intiView() {
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

            // 自定义View (一) 随机数
            case R.id.bt_custom_view_01:
                startActivity(new Intent(view.getContext(), CustomViewActivity.class));
                break;

            // 自定义View (二) 进阶
            case R.id.bt_custom_view_02:
                startActivity(new Intent(view.getContext(), CustomImageViewActivity.class));
                break;

            // 自定义View (三) 圆环交替 等待效果
            case R.id.bt_custom_view_03:
                startActivity(new Intent(view.getContext(), CustomProgressbarActivity.class));
                break;

            // 自定义View (四) 视频音量调节控制
            case R.id.bt_custom_view_04:
                startActivity(new Intent(view.getContext(), CustomAudioControlActivity.class));
                break;

            // 手把手教您自定义ViewGroup（一
            case R.id.bt_custom_view_05:
                startActivity(new Intent(view.getContext(), CustomImgContainerActivity.class));
                break;

            // ViewDragHelper完全解析 自定义ViewGroup神器
            case R.id.bt_custom_view_06:
                startActivity(new Intent(view.getContext(), VDHDeepLayoutActivity.class));
                break;

            // 自定义view实现--FlowLayout
            case R.id.bt_custom_view_07:
                startActivity(new Intent(view.getContext(), FlowLayoutActivity.class));
                break;

            // 自定义view实现--圆角图片
            case R.id.bt_custom_view_08:
                startActivity(new Intent(view.getContext(), RoundCornerImageActivity.class));
                break;

            // 自定义view实现--风骚进度条
            case R.id.bt_custom_view_09:
                startActivity(new Intent(view.getContext(), NBProgressBarActivity.class));
                break;
        }
    }
}
