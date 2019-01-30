package com.example.xpeng.app1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.tv1);
        //图文混排之左侧添加图片
        SpannableString span1= new SpannableString("冰 淇淋");
        Drawable drawable = this.getResources().getDrawable(R.mipmap.pic1);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //第一个参数drawable 第二个参数对齐方式
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        span1.setSpan(imageSpan, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(span1);

        //图文混排之添加超链接
        tv2=(TextView) findViewById(R.id.tv2);
        SpannableString span2= new SpannableString("这是一个超链接");
        URLSpan urlSpan = new URLSpan("https://www.baidu.com");
        span2.setSpan(urlSpan, 4, span2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        tv2.setHighlightColor(ContextCompat.getColor(this, R.color.colorAccent));
        tv2.setText(span2);

        //图文混排之添加背景色
        tv3=(TextView)findViewById(R.id.tv3);
        SpannableString span3 = new SpannableString("设置背景色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        span3.setSpan(colorSpan, 2, span3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv3.setText(span3);

        //图文混排之点击事件
        tv4=(TextView)findViewById(R.id.tv4);
        SpannableString span4=new SpannableString("点击事件");
        ClickableSpan clickspan=new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //设置点击事件
                Intent intent = new Intent(MainActivity.this, music.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint tp) {
                tp.setColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                tp.setUnderlineText(true);
            }
        };
        span4.setSpan(clickspan, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv4.setText(span4);
        tv4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
