package com.example.xpeng.app1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
      private static final int[] attrs=new int[]{android.R.attr.listDivider};
      private Drawable myDivider;
      private int myOrientation;
      public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
      public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

      public DividerItemDecoration(Context context, int orientation){
          final TypedArray a = context.obtainStyledAttributes(attrs);
          myDivider = a.getDrawable(0);
          a.recycle();
          setOrientation(orientation);
      }

      public void setOrientation(int orientation) {
          if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
              throw new IllegalArgumentException("invalid orientation");
          }
          myOrientation = orientation;
    }

    public void onDraw(Canvas c, RecyclerView parent) {//在Item绘制之前被调用，该方法主要用于绘制间隔样式。
        if (myOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) { //canvas用于画图
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + myDivider.getIntrinsicHeight();
            myDivider.setBounds(left, top, right, bottom);
            myDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {  //水平方向
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + myDivider.getIntrinsicHeight();
            myDivider.setBounds(left, top, right, bottom);
            myDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (myOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, myDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, myDivider.getIntrinsicWidth(), 0);
        }
    }
}