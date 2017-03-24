package com.defineview.bill.mydefineview.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.defineview.bill.mydefineview.R;
import com.defineview.bill.mydefineview.view.HorizontalProgressBarWithNumber;
import com.defineview.bill.mydefineview.view.RoundProgressBarWithNumber;

/**
 * 横向和圆形进度条
 */
public class NBProgressBarActivity extends AppCompatActivity {

    private HorizontalProgressBarWithNumber horizontalProgressBarWithNumber;
    private RoundProgressBarWithNumber mRoundProgressBarWithNumber;

    private static final int MSG_PROGRESS_UPDATE = 0x110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nbprogress_bar);
        setTitle("自定义view实现--风骚进度条");

        horizontalProgressBarWithNumber = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);
        mRoundProgressBarWithNumber = (RoundProgressBarWithNumber) findViewById(R.id.id_progressbar04);

        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
    }

    /**
     * 更新进度条
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            int progress = horizontalProgressBarWithNumber.getProgress();
            horizontalProgressBarWithNumber.setProgress(++progress);

            int progress2 = mRoundProgressBarWithNumber.getProgress();
            mRoundProgressBarWithNumber.setProgress(++progress2);

            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }

            if (progress2 >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }

            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
        }
    };

}
