package com.bee.leetcode.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bee.leetcode.R;
import com.bee.leetcode.db.bean.QuestionArrayBean;

import java.util.ArrayList;
import java.util.List;

public class QuestionListFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        view=inflater.inflate(R.layout.question_content_frag,container,false);
        return view;
    }
    //用于将题目的标题和内容显示在界面上
    public void refresh(String newsTitle,int answer_num,String passrate,String difficulty,Boolean ispassed){
        View horizontalConstraintLayout=view.findViewById(R.id.guideline);
        horizontalConstraintLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText=(TextView)view.findViewById(R.id.question_titile);
        TextView newsAnswernum=(TextView)view.findViewById(R.id.answer_num);
        TextView newsPassrate=(TextView)view.findViewById(R.id.passrate);
        TextView newsDifficulty=(TextView)view.findViewById(R.id.difficulty);
        TextView newsIspassed=(TextView)view.findViewById(R.id.ispassed);
        newsTitleText.setText(newsTitle);//刷新题目的标题
        newsAnswernum.setText(answer_num);//刷新题目的题解数
        newsPassrate.setText(passrate);//刷新题目的通过率
        newsDifficulty.setText(difficulty);//刷新题目的难度
    }

}
