package com.bee.leetcode.view.diyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    private int mSize;
    private Paint mPaint;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //进行初始化
        mPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        mSize = Math.min(mWidth,mHeight);
        //圆形应该宽高相等
        setMeasuredDimension(mSize,mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        //获得图片资源（必须从java代码中设置）
         Bitmap sourceBitmap = ((BitmapDrawable)getDrawable()).getBitmap();
         if (sourceBitmap != null){
             Bitmap newBitmap = resizeBitmap(sourceBitmap,getWidth(),getHeight());
             drawCircleBitmapByShader(canvas,newBitmap);
         }


    }
    private Bitmap resizeBitmap(Bitmap sourceBitmap,int dstWidth,int dstHeight){
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();

        //直接等比例缩放（很粗糙）
        float widthScale = ((float)dstWidth) / width;
        float heightScale = ((float)dstHeight) / height;

        //取最大缩放比
        float scale = Math.max(widthScale,heightScale);
        Matrix matrix = new Matrix();
        //两个参数分别代表了在x，y轴上的缩放倍数
        matrix.postScale(scale,scale);

        return Bitmap.createBitmap(sourceBitmap,0,0,width,height,matrix,true);
    }

    private void drawCircleBitmapByShader(Canvas canvas,Bitmap bitmap){
        //创建着色器
        BitmapShader shader = new BitmapShader(bitmap,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(mSize / 2,mSize /2 ,mSize / 2,mPaint);
    }
}
