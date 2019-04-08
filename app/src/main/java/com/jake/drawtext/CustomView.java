package com.jake.drawtext;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yinhao
 * @date 2019/4/8
 */
public class CustomView extends View {

  private Paint mPaint;
  private FontMetrics mFontMetrics;

  public CustomView(Context context) {
    super(context);
    init();
  }

  public CustomView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomView(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setTextSize(sp2px(15));
    mFontMetrics = new FontMetrics();
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    mPaint.setColor(Color.BLUE);
    mPaint.setStyle(Style.STROKE);
    mPaint.setStrokeWidth(10);
    int width = getWidth();
    int height = getHeight();
    int r = Math.min(width, height) / 2 - 10;
    float centerX = width / 2;
    float centerY = height / 2;
    //
    canvas.drawCircle(centerX, centerY, r, mPaint);
    //
    mPaint.setColor(Color.RED);
    canvas.drawLine(width / 2, height / 2 - r, width / 2,
        +height / 2 + r, mPaint);
    canvas.drawLine(+width / 2 - r, height / 2, width / 2 + r,
        height / 2, mPaint);
    //
    mPaint.setColor(Color.WHITE);
    mPaint.setStyle(Style.FILL);
    String txt = "测试文件";
    //测量文本宽度
    float x = centerX - mPaint.measureText(txt) / 2;
    mPaint.getFontMetrics(mFontMetrics);
    //计算文本基线偏移位置
    float baseLineOffsetY = (mFontMetrics.descent - mFontMetrics.ascent) / 2 - mFontMetrics.descent;
    float baseLineY = centerY + baseLineOffsetY;
    canvas.drawText(txt, x, baseLineY, mPaint);
  }

  private float sp2px(int spValue) {
    float scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
    return spValue * scaledDensity + 0.5f;
  }
}
