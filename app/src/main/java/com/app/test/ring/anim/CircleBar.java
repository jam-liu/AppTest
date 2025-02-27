package com.app.test.ring.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.app.test.util.DensityUtil;

public class CircleBar extends View {
    /**
     * 圆弧的范围框
     */
    private RectF mColorWheelRectangle = new RectF();
    /**
     * 背景画笔
     */
    private Paint mDefaultWheelPaint;
    /**
     * 圆弧的画笔
     */
    private Paint mColorWheelPaint;
    /**
     * 文字的画笔
     */
    private Paint textPaint;
    /**
     * 圆弧的半径
     */
    private float mColorWheelRadius;
    /**
     * 圆弧画笔的宽度
     */
    private float circleStrokeWidth;
    /**
     * 点击之后的圆弧的画笔宽度
     */
    private float pressExtraStrokeWidth;
    private String mText;
    private int mCount;
    /**
     * 当前弧度
     */
    private float mSweepAnglePer;
    /**
     * 最大弧度
     */
    private float mSweepAngle;
    private int mTextSize;
    private BarAnimation anim;

    public CircleBar(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        circleStrokeWidth = DensityUtil.dp2px(10);
        pressExtraStrokeWidth = DensityUtil.dp2px(2);
        mTextSize = DensityUtil.dp2px(40);

        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setColor(0xFF29a6f6);
        mColorWheelPaint.setStyle(Style.STROKE);
        mColorWheelPaint.setStrokeWidth(circleStrokeWidth);

        mDefaultWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefaultWheelPaint.setColor(0xFFeeefef);
        mDefaultWheelPaint.setStyle(Style.STROKE);
        mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setColor(0xFF333333);
        textPaint.setStyle(Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Align.LEFT);
        textPaint.setTextSize(mTextSize);

        mText = "0";
        mSweepAngle = 0;

        anim = new BarAnimation();
        anim.setDuration(2000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mColorWheelRectangle, -90, 360, false,
                mDefaultWheelPaint);
        canvas.drawArc(mColorWheelRectangle, -90, mSweepAnglePer, false,
                mColorWheelPaint);
        Rect bounds = new Rect();
        String textstr = mCount + "";
        textPaint.getTextBounds(textstr, 0, textstr.length(), bounds);
        canvas.drawText(textstr + "", (mColorWheelRectangle.centerX())
                        - (textPaint.measureText(textstr) / 2),
                mColorWheelRectangle.centerY() + bounds.height() / 2, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mColorWheelRadius = min - circleStrokeWidth - pressExtraStrokeWidth;

        mColorWheelRectangle.set(circleStrokeWidth + pressExtraStrokeWidth,
                circleStrokeWidth + pressExtraStrokeWidth, mColorWheelRadius,
                mColorWheelRadius);
    }

    @Override
    public void setPressed(boolean pressed) {

        Log.i("jam", "call setPressed ");

        if (pressed) {
            mColorWheelPaint.setColor(0xFF165da6);
            textPaint.setColor(0xFF070707);
            mColorWheelPaint.setStrokeWidth(circleStrokeWidth
                    + pressExtraStrokeWidth);
            mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth
                    + pressExtraStrokeWidth);
            textPaint.setTextSize(mTextSize - pressExtraStrokeWidth);
        } else {
            mColorWheelPaint.setColor(0xFF29a6f6);
            textPaint.setColor(0xFF333333);
            mColorWheelPaint.setStrokeWidth(circleStrokeWidth);
            mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth);
            textPaint.setTextSize(mTextSize);
        }
        super.setPressed(pressed);
        this.invalidate();
    }

    public void startCustomAnimation() {
        this.startAnimation(anim);
    }

    public void setText(String text) {
        mText = text;
        this.startAnimation(anim);
    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = sweepAngle;

    }

    public class BarAnimation extends Animation {
        /**
         * Initializes expand collapse animation, has two types, collapse (1)
         * and expand (0).
         *
         * @param view The view to animate
         * @param type The type of animation: 0 will expand from gone and 0 size
         *             to visible and layout size defined in xml. 1 will collapse
         *             view and set to gone
         */
        public BarAnimation() {

        }

        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                mSweepAnglePer = interpolatedTime * mSweepAngle;
                mCount = (int) (interpolatedTime * Float.parseFloat(mText));
            } else {
                mSweepAnglePer = mSweepAngle;
                mCount = Integer.parseInt(mText);
            }
            postInvalidate();
        }
    }
}
