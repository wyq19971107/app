package com.example.xpeng.app1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class music extends Activity {
    private RecyclerView rv;
    private RecyclerAdaper adapter;
    //private wn_adapter adapter1;
    public static ArrayList<String> data=new ArrayList<String>();
    String TAG="music";
    LinearLayoutManager layoutManager1;
    GridLayoutManager layoutManager2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        Intent intent = getIntent();

        rv = (RecyclerView) findViewById(R.id.rv);
        //切换屏幕时保存数据，如有数据，则不再进行数据的初始化
        if (savedInstanceState != null && savedInstanceState.getStringArrayList("data") != null) {
            data = savedInstanceState.getStringArrayList("data");
        }else {
            initData();
        }
        layoutManager1=new LinearLayoutManager(this);//竖屏线性显示
        layoutManager2=new GridLayoutManager(this,2);//横屏显示2列
        adapter=new RecyclerAdaper();

       /* adapter1=new wn_adapter<ColorSpace.Model>(data) {

            public int getItemViewType(int position) {
                if(position % 2 == 0){
                    return 1;
                } else{
                    return 2;
                }
            }

            @Override
            public int getLayoutId(int viewType) {
                  switch (viewType){
                      case 1:
                          return R.layout.item1;
                      case 2:
                          return R.layout.item2;
                  }
            }

            @Override
            public void convert(VH holder, ColorSpace.Model data, int position) {
                int type = getItemViewType(position);
                switch(type){
                    case 1:
                        holder.setText(R.id.text, data.text);
                        break;
                    case 2:
                        holder.setImage(R.id.image, data.image);
                        break;
                }
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        };*/

        reviewonScreenChanged(getResources().getConfiguration());
        //设置Adapter
        rv.setAdapter(adapter);
        //设置增加或删除条目的动画
         rv.setItemAnimator( new DefaultItemAnimator());

         //滑动监听
      /*  rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dx>0 则表示 右滑 ,dx<0 表示 左滑;dy <0 表示 上滑, dy>0 表示下滑
                super.onScrolled(recyclerView, dx, dy);

            }
        });*/

        adapter.setOnItemClickListener(new RecyclerAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {   //dan

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

     //判断手机是横屏还是竖屏
    private void reviewonScreenChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            rv.setLayoutManager(layoutManager2);
            rv.addItemDecoration( new DividerGridItemDecoration(this));
        }else {
            //竖屏
            rv.setLayoutManager(layoutManager1);
            rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        reviewonScreenChanged(newConfig);
    }

    protected void initData()
    {
        for (int i = 'A'; i < 'z'; i++)
        {
            data.add("" + (char) i);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("data", data);
    }

    public void onclick(View v){  //按钮响应事件
        int id = v.getId();
        if(id == R.id.add) {
            adapter.addNewItem();
            // 在首个Item位置做增加操作
            layoutManager1.scrollToPosition(0);
        } else if(id == R.id.remove){
            adapter.deleteItem();
            // 在首个Item位置做删除操作
            layoutManager1.scrollToPosition(0);
        }
    }

}
