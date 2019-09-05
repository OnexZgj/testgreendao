package com.inspur.greendao;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.inspur.greendao.utils.OnexUtils;

import java.util.Calendar;

public class CircleClockView extends View {

    private float mWidth;

    private float mHeight;
    /**
     * 时钟的半径
     */
    private float mHourR;
    /**
     * 分钟的半径
     */
    private float mMinuteR;
    /**
     * 秒针的半径
     */
    private float mSecondR;


    private Paint mPaint = new Paint();

    private Paint mHelpPaint = new Paint();
    private String TAG = "TAG";
    private int hour;
    private int minute;
    private int month;
    private int second;
    private float mHourDeg = 0;
    private float mMinuteDeg = 0;
    private float mSecondDeg = 0;
    private int day;
    private int dayOfWeek;
    private ValueAnimator mAnimator;

    public CircleClockView(Context context) {
        this(context, null);
    }

    public CircleClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doInvalidate();

        Log.d(TAG, "CircleClockView: 构造方法");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingBottom() - getPaddingTop();

        //后文会涉及到
        //统一用View宽度*系数来处理大小，这样可以联动适配样式
        mHourR = mWidth * 0.143f;
        mMinuteR = mWidth * 0.35f;
        mSecondR = mWidth * 0.35f;



    }


    public void doInvalidate() {

        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minute = Calendar.getInstance().get(Calendar.MINUTE);
        second = Calendar.getInstance().get(Calendar.SECOND);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;

        Log.d(TAG, "drawCenterInfo: " + hour + ": " + minute + ": " + month + ": " + day + ": " + dayOfWeek);


        mHourDeg = -360 / 12f * (hour);
        mMinuteDeg = -360 / 60f * (minute);
        mSecondDeg = -360 / 60f * (second);

        Log.d(TAG, "invalidate: " + mHourDeg + ":" + mMinuteDeg + ":" + mSecondDeg);
        //处理动画，声明全局的处理器
        mAnimator = ValueAnimator.ofFloat(6f, 0f);//由6降到1
        mAnimator.setDuration(150);
        mAnimator.setInterpolator(new LinearInterpolator());//插值器设为线性

        //记录当前角度，然后让秒圈线性的旋转6°
        float hd = mHourDeg;
        float md = mMinuteDeg;
        float sd = mSecondDeg;

        //处理动画
        mAnimator.removeAllUpdateListeners();//需要移除先前的监听
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float av = (float) valueAnimator.getAnimatedValue();

                Log.d(TAG, "onAnimationUpdate: " + av);

                Log.d(TAG, "onAnimationUpdate: min" + minute + " second: " + second);
                if (minute == 0 && second == 0) {
                    mHourDeg = hd + av * 5;
                }

                if (second == 0) {
                    mMinuteDeg = md + av;//线性的旋转6°
                }

                mSecondDeg = sd + av;//线性的旋转6°

                invalidate();
            }

        });
        mAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);


        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mHelpPaint.setColor(Color.RED);

        canvas.drawLine(getWidth() / 2, 0f, getWidth() / 2, getHeight(), mHelpPaint);
        canvas.drawLine(0f, getHeight() / 2, getWidth(), getHeight() / 2, mHelpPaint);


        canvas.drawColor(Color.BLACK);//填充背景
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);//原点移动到中心

        //绘制各元件，后文会涉及到
        drawCenterInfo(canvas);

        drawHour(canvas, mHourDeg);

        Log.d(TAG, "onDraw: " + mHourDeg);
        drawMinute(canvas, mMinuteDeg);
        Log.d(TAG, "onDraw: " + mMinuteDeg);
        drawSecond(canvas, mSecondDeg);
        Log.d(TAG, "onDraw: " + mSecondDeg);

        //从原点处向右画一条辅助线，之后要处理文字与x轴的对齐问题，稍后再说
//        canvas.drawLine(0f, 0f, mWidth, 0f, mHelperPaint);

        canvas.restore();
    }

    private void drawSecond(Canvas canvas, float mSecondDeg) {
        mPaint.setTextSize(mHourR * 0.16f);

        //处理整体旋转
        canvas.save();
        canvas.rotate(mSecondDeg);

        for (int i = 0; i < 60; i++) {
            canvas.save();

            float iDeg = 360 / 60f * i;
            canvas.rotate(iDeg);

//            mPaint.alpha = if (iDeg + degrees == 0f) 255 else (0.6f * 255).toInt()
            mPaint.setTextAlign(Paint.Align.LEFT);

            int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);

            if (i <= 59) {
                canvas.drawText(i + "秒", mSecondR + 20, textHeight, mPaint);
            }
            canvas.restore();
        }
        canvas.restore();
    }

    private void drawMinute(Canvas canvas, float mMinuteDeg) {
        mPaint.setTextSize(mHourR * 0.16f);

        //处理整体旋转
        canvas.save();
        canvas.rotate(mMinuteDeg);

        for (int i = 0; i < 60; i++) {
            canvas.save();

            float iDeg = 360 / 60f * i;
            canvas.rotate(iDeg);

//            mPaint.alpha = if (iDeg + degrees == 0f) 255 else (0.6f * 255).toInt()
            mPaint.setTextAlign(Paint.Align.RIGHT);

            int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);

            if (i <= 59) {
                canvas.drawText(i + "分", mMinuteR, textHeight, mPaint);
            }
            canvas.restore();
        }
        canvas.restore();
    }


    /**
     * 绘制小时
     */
    private void drawHour(Canvas canvas, Float degrees) {
        mPaint.setTextSize(mHourR * 0.16f);

        //处理整体旋转
        canvas.save();
        canvas.rotate(degrees);


        for (int i = 0; i < 12; i++) {
            canvas.save();

            //从x轴开始旋转，每30°绘制一下「几点」，12次就画完了「时圈」
            float iDeg = 360 / 12f * i;
            canvas.rotate(iDeg);

//            mPaint.alpha = if (iDeg + degrees == 0f) 255 else (0.6f * 255).toInt();
            mPaint.setTextAlign(Paint.Align.LEFT);

            int textWidth = (int) mPaint.measureText(hour + ":" + minute);
            int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);
            String textHour = OnexUtils.toHourText(i);
            canvas.drawText(textHour + " 点", mHourR, textHeight, mPaint);
            canvas.restore();
        }

        canvas.restore();
    }


    /**
     * 画圆中心空间
     *
     * @param canvas
     */
    private void drawCenterInfo(Canvas canvas) {

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);

        canvas.drawLine(0f, 0f, getWidth(), 0f, mPaint);
        canvas.drawLine(0f, 0f, 0f, getHeight(), mPaint);


        String dayofWeek = OnexUtils.toText(dayOfWeek);


        mPaint.setTextSize(mHourR * 0.4f); //字体大小根据「时圈」半径来计算
        mPaint.setAlpha(255);
        mPaint.setTextAlign(Paint.Align.CENTER);

        int textWidth = (int) mPaint.measureText(hour + ":" + minute);
        int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);

        canvas.drawText(hour + ":" + minute, 0f, 0f, mPaint);

        mPaint.setTextSize(mHourR * 0.16f); //字体大小根据「时圈」半径来计算
        mPaint.setAlpha(255);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(month + "." + day + " 星期" + dayofWeek, 0f, 50f, mPaint);
    }


}

