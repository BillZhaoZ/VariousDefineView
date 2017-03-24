package com.defineview.bill.mydefineview.viewgroup;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * ViewDragHelper完全解析 自定义ViewGroup神器:
 * Created by Bill on 2017/3/8.
 */
public class VDHDeepLayout extends LinearLayout {

    private ViewDragHelper mDragHelper;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgerackerView;

    private Point mAutoBackOriginPos = new Point();

    public VDHDeepLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * 创建实例需要3个参数，第一个就是当前的ViewGroup，第二个sensitivity，主要用于设置touchSlop:
         */
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            /**
             * 如何返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                //mEdgeTrackerView禁止直接移动
                return child == mDragView || child == mAutoBackView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {

                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView) {
                    mDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
            }

            // 边界拖拽处理
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mDragHelper.captureChildView(mEdgerackerView, pointerId);
            }

            // clickable=true，意思就是子View可以消耗事件
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
        });

        // 允许边界检测
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
    }

    /**
     * 拦截事件和触摸事件交给viewdragerhelp处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        // 获取可拖拽的起始位置
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 定义三种类型的view
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgerackerView = getChildAt(2);
    }

    @Override
    public void computeScroll() {

        // 界面重绘  回到起始位置
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
