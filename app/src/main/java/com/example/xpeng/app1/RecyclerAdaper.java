package com.example.xpeng.app1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.VH>{

    private RecyclerAdaper.OnItemClickListener onItemClickListener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        VH holder=new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        return holder;
    }

    public void onBindViewHolder(final VH holder, int position) {   //用于适配渲染数据到View
        holder.title.setText(music.data.get(position));

        //③ 对RecyclerView的每一个itemView设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;
        public VH(View view){
                 super(view);
                 title=(TextView)view.findViewById(R.id.item_tv);
             }
         }

    @Override
    public int getItemCount() {   //总共有多少个条目。
        return music.data.size();
    }

    //添加删除item
    public void addNewItem() {
        if(music.data == null) {
            music.data = new ArrayList<>();
        }
        music.data.add(0, "new Item");
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if(music.data == null || music.data.isEmpty()) {
            return;
        }
        music.data.remove(0);
        notifyItemRemoved(0);
    }

    //单击打开新页面
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(RecyclerAdaper.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}
