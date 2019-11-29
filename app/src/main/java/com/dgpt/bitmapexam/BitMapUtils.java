package com.dgpt.bitmapexam;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

public class BitMapUtils {
    /**
     * 缩放图片
     */
    public static void bitmapScale(Bitmap baseBitmap, ImageView iv_after, Paint paint, float x, float y) {
        // 由于要将图片放大。所以要依据放大的尺寸又一次创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 依据传入的參数设置缩放比例
        matrix.setScale(x, y);
        // 依据缩放比例。把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


    /**
     * x轴镜像
     */
    public static void bitmapXMirror(Bitmap baseBitmap, ImageView iv_after, Paint paint) {
        // 由于要将图片放大，所以要依据放大的尺寸又一次创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                baseBitmap.getWidth() ,
                baseBitmap.getHeight() , baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 依据传入的參数设置缩放比例
        matrix.postScale(-1, 1);
        matrix.postTranslate(baseBitmap.getWidth(), 0);
        // 依据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


    /**
     * y轴镜像
     */
    public static void bitmapYMirror(Bitmap baseBitmap, ImageView iv_after, Paint paint) {

        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 依据传入的參数设置缩放比例
        matrix.postScale(1, -1);
        //依据变换矩阵。绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }




    /**
     * 倾斜图片
     */
    public static void bitmapSkew(Bitmap baseBitmap, ImageView iv_after, Paint paint, float dx, float dy) {
        // 依据图片的倾斜比例。计算变换后图片的大小，
        Matrix matrix = new Matrix();
        // 设置图片倾斜的比例
        matrix.setSkew(dx, dy);
        //依据变换矩阵，绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片移动
     */
    public static void bitmapTranslate(Bitmap baseBitmap, ImageView iv_after, Paint paint, float dx, float dy) {
        // 须要依据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    public static void bitmapRotate(Bitmap baseBitmap, ImageView iv_after, Paint paint, float degrees) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 依据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 转换成圆角
     *
     * @param bmp
     * @param roundPx
     * @return
     */
    public static void convertToRoundedCorner(Bitmap bmp, ImageView iv_after, Paint paint, float roundPx) {

        Bitmap newBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        // 得到画布
        Canvas canvas = new Canvas(newBmp);

        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        final RectF rectF = new RectF(rect);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // 第二个和第三个参数一样则画的是正圆的一角，否则是椭圆的一角
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);
        iv_after.setImageBitmap(newBmp);

    }

    /**
     * 图片特效处理
     * @param bmp  传入的图片
     * @param colorArray 颜色矩阵值
     * @return
     */
    public static void toProcessColor(Bitmap bmp, ImageView iv_after, Paint paint, float[] colorArray){

            int width, height;
            height = bmp.getHeight();
            width = bmp.getWidth();
            Bitmap bm = Bitmap.createBitmap(width, height,
                    Bitmap.Config.RGB_565);
            Canvas canvas=new Canvas(bm);
            ColorMatrix myColorMatrix = new ColorMatrix();
            myColorMatrix.set(colorArray);
            paint.setColorFilter(new ColorMatrixColorFilter(myColorMatrix));
            canvas.drawBitmap(bmp,0,0,paint);
            iv_after.setImageBitmap(bm);
    }

}
