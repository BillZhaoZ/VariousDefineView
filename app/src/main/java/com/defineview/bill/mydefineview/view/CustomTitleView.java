package com.defineview.bill.mydefineview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.defineview.bill.mydefineview.R;

import java.util.HashSet;
import java.util.Random;

/**
 * 自定义view
 * Created by Bill on 2017/3/6.
 */
public class CustomTitleView extends View {

    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context) {
        this(context, null);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Log.i("CustomTitleView方法执行了。。。", context.getPackageName());

        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        //mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        // 设置点击事件
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText = randomext();
                postInvalidate();
            }
        });

    }

    /**
     * 获取随机数
     *
     * @return
     */
    private String randomext() {

        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();

        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuffer buffer = new StringBuffer();
        for (Integer i : set) {
            buffer.append("" + i);
        }

        return buffer.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("onMeasure方法执行了。。。", String.valueOf(widthMeasureSpec + heightMeasureSpec));

        int width = 0;
        int height = 0;

        // 获取测量模式
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取宽度
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;

            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + getPaddingRight() + mBound.width();
                break;

        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取高度
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;

            case MeasureSpec.AT_MOST:
                height = getPaddingTop() + getPaddingBottom() + mBound.height();
                break;

        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        Log.i("onDraw方法执行了。。。", canvas.toString());

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);

        /**
         * 用的是其中一个drawText重载方法:canvas.drawText(String text,float x,float y,Paint paint);
         * x和y是绘制时的起点坐标, getWidth() / 2 - mRect.width() / 2 其实是为了居中绘制文本,
         * getWidth()是获取自定义View的宽度,
         * mRect.width()是获取文本的宽度,
         * 你可能是想问为什么不是直接getWidth() / 2吧?
         * 这样的话文本就是从水平方向中间位置向右绘制,绘制的文本当然就不是居中了,要减去mRect.width() / 2才是水平居中.垂直方向同理.
         *
         * "0 + getPaddingLeft()": 绘制文本的起点X
         *   "0": 直接从"0"开始就可以(文字会自带一点默认间距)
         *
         * */

        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2 - mBound.left, getHeight() / 2 + mBound.height() / 2, mPaint);

        // 这样写会自动居中(但会有一点误差,以为文字会自带一点默认间距," - mRect.left"就好,csdn:[Zohar_zou]解决)
        // canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
