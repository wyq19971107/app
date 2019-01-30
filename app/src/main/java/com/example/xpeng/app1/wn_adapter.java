package com.example.xpeng.app1;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public abstract class wn_adapter<T> extends RecyclerView.Adapter<wn_adapter.VH> {
    private List<T> mydata;

    public wn_adapter(List<T> data) {
        this.mydata = data;
    }

    public abstract int getLayoutId(int viewType);

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mydata.get(position), position);
    }

    public int getItemCount() {
        return mydata.size();
    }

    public abstract void convert(VH holder, T data, int position);

    static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> sparseView;    //用于存储item View
        private View myView;

        private VH(View v) {
            super(v);
            myView = v;
            sparseView = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = sparseView.get(id);
            if (v == null) {
                v = myView.findViewById(id);
                sparseView.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }
    }
}

