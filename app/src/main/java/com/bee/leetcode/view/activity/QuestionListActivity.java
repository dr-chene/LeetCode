package com.bee.leetcode.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bee.leetcode.R;
import com.bee.leetcode.adapter.BaseBindingAdapter;
import com.bee.leetcode.db.bean.QuestionArrayBean;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private List<QuestionArrayBean> questionArrayBeanList=new ArrayList<QuestionArrayBean>();
   private ProgressDialog progressDialog;
   private QustionListAdapter qustionListAdapter;
   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_questionlist);
       questionArrayBeanList=(RecyclerView)findViewById(R.id.);
       //实例化进度条对话框
       progressDialog=new ProgressDialog(this);
       progressDialog.setMessage("正在加载中....");
       //实例化适配器
   }


}
