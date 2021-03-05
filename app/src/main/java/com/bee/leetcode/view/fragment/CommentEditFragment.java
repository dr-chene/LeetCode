package com.bee.leetcode.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bee.leetcode.R;
import com.bee.leetcode.base.BaseFragment;
import com.bee.leetcode.databinding.ActivityMainBinding;
import com.bee.leetcode.databinding.FragmentCommentEditBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * created by dr_chene on 2021/3/4
 * desc 编辑、发送评论页
 */
public class CommentEditFragment extends BottomSheetDialogFragment {

    private FragmentCommentEditBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment_edit, container, false);
        if (getContext() == null) return binding.getRoot();

        initView();
        initAction();

        return binding.getRoot();
    }

    private void initView() {
        //弹出键盘
        binding.fragmentCommentEditEt.requestFocus();
    }

    private void initAction() {
        binding.fragmentCommentEditTvSend.setOnClickListener(v -> {
            // TODO: 2021/3/5 获取parentId和userId
            sendComment(0, 0, binding.fragmentCommentEditEt.getText().toString());
        });
    }

    private void sendComment(int parentId, int userId, String comment){
        // TODO: 2021/3/5 发送评论
    }
}
