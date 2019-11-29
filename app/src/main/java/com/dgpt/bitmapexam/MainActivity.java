package com.dgpt.bitmapexam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btn_scale, btn_rotate, btn_translate, btn_skew,btn_XMirror,btn_YMirror;
    private ImageView iv_base, iv_after;
    private Bitmap baseBitmap;
    private Paint paint;
    private Button btnYuanjiao;
    private Button btnColormatrix;
    private float[] colorArray = {  1, 0, 0, 0, 0,
            0, 1, 0, 0, 1,
            2, 0, 1, 0, 0,
            0, 0, 0, 1, 0 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_XMirror = (Button) findViewById(R.id.btn_x_mirror);
        btn_YMirror = (Button) findViewById(R.id.btn_y_mirror);
        btnYuanjiao = (Button) findViewById(R.id.btn_yuanjiao);
        btnColormatrix = (Button) findViewById(R.id.btn_colormatrix);

        btn_scale.setOnClickListener(click);
        btn_rotate.setOnClickListener(click);
        btn_translate.setOnClickListener(click);
        btn_skew.setOnClickListener(click);
        btn_XMirror.setOnClickListener(click);
        btn_YMirror.setOnClickListener(click);
        btnYuanjiao.setOnClickListener(click);
        btnColormatrix.setOnClickListener(click);

        iv_base = (ImageView) findViewById(R.id.iv_base);
        iv_after = (ImageView) findViewById(R.id.iv_after);

        baseBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.d);
        iv_base.setImageBitmap(baseBitmap);

        // 设置画笔，消除锯齿
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_scale:
                    BitMapUtils.bitmapScale(baseBitmap, iv_after, paint,2.0f, 4.0f);;
                    break;
                case R.id.btn_rotate:
                    BitMapUtils.bitmapRotate(baseBitmap, iv_after, paint,180);
                    break;
                case R.id.btn_translate:
                    BitMapUtils.bitmapTranslate(baseBitmap, iv_after, paint,20f, 20f);
                    break;
                case R.id.btn_skew:
                    BitMapUtils.bitmapSkew(baseBitmap, iv_after, paint,0.2f, 0.4f);
                    break;
                case R.id.btn_x_mirror:
                    BitMapUtils.bitmapXMirror(baseBitmap, iv_after, paint);
                    break;
                case R.id.btn_y_mirror:
                    BitMapUtils.bitmapYMirror(baseBitmap, iv_after, paint);
                    break;
                case R.id.btn_yuanjiao:
                    BitMapUtils.convertToRoundedCorner(baseBitmap, iv_after, paint, 60);
                    break;
                case R.id.btn_colormatrix:
                    BitMapUtils.toProcessColor(baseBitmap, iv_after, paint,colorArray);
                    break;
                default:
                    break;
            }

        }
    };


}
