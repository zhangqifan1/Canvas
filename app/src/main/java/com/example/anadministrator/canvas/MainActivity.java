package com.example.anadministrator.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView1(this));


    }

    /**
     * 使用内部类 自定义一个简单的View
     */
    class CustomView1 extends View {

        Paint paint;

        public CustomView1(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            /*******总的来说几乎画任何图形 都有起始坐标*******/


//            canvas.drawColor(Color.GREEN);//drawColor 是直接将View 显示区域用某个颜色填充满
//            canvas.drawCircle(200,200,90,paint); //画圆


            /******化弧线******/
            //绘制弧线区域
//            RectF rectF = new RectF(0, 0, 100, 100);

//            canvas.drawArc(rectF,//弧线所使用的矩形区域大小
//                    0,//开始角度
//                    90,//扫过的角度
//                    true,//是否使用中心   选中心的话 挺大的刚好4分之1
//                    paint
//                    );
            //可以发现 当为false 时 弧线区域使用弧线开始角度和结束角度 直接连接起来的,当为true时,是弧线开始角度和结束角度都与中心点连接,形成一个扇形.

            /***************画一条线*******************/
//            canvas.drawLine(10,10,100,300,paint);

            /**********画一个椭圆*************/
//            RectF rectF = new RectF(30, 30, 200, 300);
//            canvas.drawOval(rectF,paint);

            /********绘制特殊文本*******/
            //按照既定点 绘制文本内容
//            paint.setTextSize(25);
//            canvas.drawPosText("一统江湖",new float[]{
//                    30,30,//第一个字母的坐标10 10
//                    50,50,//第二个
//                    70,70,
//                    100,100
//            },paint);

            /*************画矩形,正方形也是特殊的矩形***************/
//            RectF rectF = new RectF(50, 50, 200, 400);
//            canvas.drawRect(rectF,paint);

            /************画圆角矩形************/
//            RectF rectF = new RectF(50, 50, 200, 200);
//            canvas.drawRoundRect(rectF,
//                    30,//X轴的半径
//                    30,//y轴的半径
//                    paint );
            /************画三角形*************/
            // 通过不断的改变坐标点  ,用轨迹来画三角形 moveTo  起始点   lineTo 则是具体画的轨迹
//            Path path = new Path(); //定义一条路径
//            path.moveTo(10, 10); //移动到 坐标10,10
//            path.lineTo(50, 60);
//            path.lineTo(200,80);
//            path.lineTo(10, 10);
//
//            canvas.drawPath(path, paint);

            /**************在一条路径上 写文字 ****************/
//            Path path = new Path(); //定义一条路径
//            path.moveTo(10, 10); //移动到 坐标10,10
//            path.lineTo(50, 60);
//            path.lineTo(200,80);
//            path.lineTo(10, 10);
//            paint.setTextSize(28);
//            canvas.drawTextOnPath("千秋万载万载千秋",path,10,10,paint);

            /************位置转换 *******************/
            System.out.println("宽高:" + canvas.getWidth() + canvas.getHeight());
            paint.setAntiAlias(true);//设置抗锯齿
            paint.setStyle(Paint.Style.STROKE);//设置空心
            canvas.translate(canvas.getWidth() / 2, canvas.getHeight()/2);
            canvas.drawCircle(0, 0, 100, paint);
            canvas.save();//使用动画后 要保存状态  还有一个Restore是取出来
            //使用path绘制路径文字
            canvas.translate(-75, -75);

            Path path = new Path();
            path.addArc(new RectF(0, 0, 150, 150),
                    -180,//开始的度数
                    180//结束的角度
            );
            Paint citePaint=new Paint(paint);
            citePaint.setTextSize(15);
            citePaint.setStrokeWidth(1);
            canvas.drawTextOnPath("一统江湖,千秋万载",path,60,0,citePaint);
            canvas.restore();

            Paint paint1=new Paint(paint);//重新做一个画笔 小刻度的
            paint1.setStrokeWidth(1);

            float y=100;
            int count=60;//总刻度数
            for (int i = 0; i <count; i++) {
                //如果是%5=0 那么需要写一个数字  画一条相对长一点的线
                if(i%5 == 0){
                    canvas.drawLine(0f,y,0,y+12f,paint);
                    canvas.drawText(String.valueOf(i/5+1),-4f,y+25f,paint1);
                }else{
                    canvas.drawLine(0f,y,0f,y+5f,paint1);
                }
                canvas.rotate(360/count,0f,0f);//旋转画纸
            }
            //绘制指针
            paint1.setColor(Color.GRAY);
            paint1.setStrokeWidth(4);
            canvas.drawCircle(0,0,7,paint1);
            paint1.setStyle(Paint.Style.FILL);
            paint1.setColor(Color.YELLOW);
            canvas.drawCircle(0,0,5,paint1);
            canvas.drawLine(0,8,0,-65,paint);//第二 四个参数影响长短  1 3影响指向 不能太大
        }
    }


    class CustomView2 extends View{

        Paint paint;
        private ArrayList<PointF> graphics = new ArrayList<PointF>();
        PointF point;

        public CustomView2(Context context) {
            super(context);
            paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.BLACK);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            graphics.add(new PointF(event.getX(),event.getY()));

            invalidate(); //重新绘制区域

            return true;
        }

        //在这里我们将测试canvas提供的绘制图形方法
        @Override
        protected void onDraw(Canvas canvas) {
            for (PointF point : graphics) {
                canvas.drawPoint(point.x, point.y, paint);
            }
//          super.onDraw(canvas);

        }
    }

}

/**
 * paint.setAntiAlias(true);       //防锯齿
 * paint.setDither(true);           //防抖动
 * paint.setStyle(Paint.Style.STROKE);         //画笔类型 STROKE空心 FILL 实心 FILL_AND_STROKE 用契形填充
 * paint.setStrokeJoin(Paint.Join.ROUND);     //画笔接洽点类型 如影响矩形但角的外轮廓
 * paint.setStrokeCap(Paint.Cap.ROUND);     //画笔笔刷类型 如影响画笔但始末端
 * mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置外边缘
 * mPaint.setStrokeCap(Paint.Cap.SQUARE);// 形状
 * mPaint.setStrokeWidth(5);// 画笔宽度
 */