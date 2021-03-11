package com.bee.leetcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
//使用BaseBindingAdapter的举列子
public class MainActivityAdapter extends BaseBindingAdapter<ShowDataBeans,BaseBindingAdapter.ViewHolder>{
    public MainActivityAdapter(Context context){
        super(context);
    }

    @Override
    public  ViewHolder onCreateVH(ViewGroup parent,int viewType){
        ViewDataBinding binding= DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.activity_main,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindVH(ViewHolder holder, int position) {
        ViewDataBinding binding=holder.getBinding();
        binding.setVariable(BR.databeans,mDataList.get(position));//绑定数据
        binding.executePendingBindings();//防止闪烁
    }
}
