package com.bee.leetcode;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//上拉进行加载，下拉刷新进行封装
public abstract class BaseBindingAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public List<T> mDataList;
    public Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding mDataBinding;

        public ViewHolder(ViewDataBinding binding){
            super(binding.getRoot());
            mDataBinding=binding;
        }

        public ViewDataBinding getBinding(){
            return mDataBinding;
        }
    }

    public BaseBindingAdapter(Context context){
        mContext=context;
        mDataList=new ArrayList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent,int viewType){
        return onCreateVH(parent,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder,int position){
        onBindVH(holder,position);
    }

    @Override
    public int getItemCount(){
        return mDataList.size();
    }

    public void onRefreshData(List<T> list) {
        if (mDataList != null) {
            mDataList.clear();
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void onLoadMoreData(List<T> list) {
        if (mDataList != null && list != null) {
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public abstract VH onCreateVH(ViewGroup parent, int viewType);
    public abstract void onBindVH(VH holder, int position);
}
