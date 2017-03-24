package com.defineview.bill.mydefineview.viewgroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 流式布局
 * Created by Bill on 2017/3/9.
 */
public class FlowLayout extends ViewGroup {

    // 行间距   列间距
    private int horientalSpacing = 10;
    private int verticalSpacing = 10;

    /**
     * 存放所有行高的集合
     */
    ArrayList<ArrayList<View>> allLinesList = new ArrayList<ArrayList<View>>();

    public FlowLayout(Context context) {
        super(context);
    }

    /**
     * 测量FlowLayout和他的子view
     *
     * @param widthMeasureSpec  父容器希望FlowLayout的宽
     * @param heightMeasureSpec 父容器希望FlowLayout的高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 因为onMeasure 会执行多次  所以每次运行前  先清空集合
        allLinesList.clear();

        // 获取FlowLayout测量的宽
        int containerWidth = MeasureSpec.getSize(widthMeasureSpec);

        // 保存一行的view对象
        ArrayList<View> onelineList = null;

        // 给子view传未指定的测量规格
        // child.measure(0, 0)传的两个0，其实它表示的是一个未指定模式并且大小为0的测量规格，
        // 相当于调用MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)创建的测量规格
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            // 测量规格传给子view  让其完成测量
            child.measure(0, 0);

            // 如果是第一行  或者剩余的空间放不下一个控件时  创建新行
            if (i == 0 || child.getMeasuredWidth() > getUsableWidth(containerWidth, onelineList)) {
                onelineList = new ArrayList<View>();
                allLinesList.add(onelineList);
            }

            onelineList.add(child);
        }

        // 获取子view测量的高  用于容器的测量高   父容器的高加上两个padding值
        int containerHeight = getAllLinesHeight() + getPaddingBottom() + getPaddingTop();

        setMeasuredDimension(containerWidth, containerHeight);
    }

    /**
     * 获取所有行的高度
     *
     * @return
     */
    private int getAllLinesHeight() {
        if (allLinesList.isEmpty()) {
            return 0;
        } else {
            // 加上间距    除了第一行，其它行都需要加上垂直间距，垂直间距个数为行数 - 1
            int allSpacing = verticalSpacing * (allLinesList.size() - 1);

            return getChildAt(0).getMeasuredHeight() * allLinesList.size() + allSpacing;
        }
    }

    /**
     * 获取可用的宽度
     *
     * @param containerWidth
     * @param onelineList
     * @return
     */
    private int getUsableWidth(int containerWidth, ArrayList<View> onelineList) {
        return containerWidth - (getPaddingLeft() + getPaddingRight()) - getOneLineWidth(onelineList);
    }

    /**
     * 获取一行可用宽度
     *
     * @param list
     * @return
     */
    private int getOneLineWidth(ArrayList<View> list) {
        int oneLineWidth = 0;
        for (View view : list) {
            oneLineWidth += view.getMeasuredWidth();
        }

        // 加上水平间距   除了第一列，其它列都需要加上一个水平间距，水平间距个数为一行View的数量 - 1
        int allSpacing = horientalSpacing * (list.size() - 1);
        return oneLineWidth + allSpacing;
    }

    /**
     * 对所有的子类进行排版
     * 可能被多次调用
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 临时当前行的底部坐标
        int tempBottom = 0;

        /**
         * 遍历所有的行
         */
        for (int i = 0; i < allLinesList.size(); i++) {
            // 获取一行
            ArrayList<View> oneLine = allLinesList.get(i);

            /**
             *  遍历一行中所有的列
             */

            // 临时保存当前view的right坐标
            int tempRight = 0;

            // 计算每一行view剩余的平均宽度
            int averageUsableWidth = getUsableWidth(getMeasuredWidth(), oneLine) / oneLine.size();

            for (int j = 0; j < oneLine.size(); j++) {
                // 获取子view
                View child = oneLine.get(j);

                // 获取子view的测量宽和高
                int childMeasuredWidth = child.getMeasuredWidth();
                int childMeasuredHeight = child.getMeasuredHeight();

                // 如果是第一列的子view  则他的left坐标为paddingLeft   否则，子view的left坐标为上一个view的right坐标
                int childLeft = j == 0 ? getPaddingLeft() : tempRight + horientalSpacing;

                // 如果是第一行的子view  则他的top坐标为paddingTop   否则，子view的left坐标为上一个view的bottom坐标
                int childTop = i == 0 ? getPaddingTop() : tempBottom + verticalSpacing;

                // 如果是一行中最后一个View，则把它的right指定到容器的paddingRight的地方
                int childRight = j == oneLine.size() - 1 ? getMeasuredWidth() - getPaddingRight() : childLeft + childMeasuredWidth + averageUsableWidth;  // 剩余的平均宽度分给每一个view
                int childBottom = childTop + childMeasuredHeight;

                // 设置子view的位置  位置确定后  子view的大小就确定了
                child.layout(childLeft, childTop, childRight, childBottom);

                // 由于子view的宽被改变了  需要重新测量  以便让view的文字居中
                int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childRight - childLeft, MeasureSpec.EXACTLY);
                int heightMeasureSpec = MeasureSpec.makeMeasureSpec(childBottom - childTop, MeasureSpec.EXACTLY);

                child.measure(widthMeasureSpec, heightMeasureSpec);

                // 保存当前列的right坐标  用于下次使用
                tempRight = childRight;
            }

            // 保存当前列的bottom坐标  用于下次使用
            tempBottom = oneLine.get(0).getBottom();
            System.out.println("tempBottom" + tempBottom);
        }
    }
}
