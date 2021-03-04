package com.bee.leetcode.view.activity;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bee.leetcode.R;
import com.bee.leetcode.base.BaseActivity;
import com.bee.leetcode.base.BaseFragment;
import com.bee.leetcode.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final FragmentStateAdapter adapter = new FragmentStateAdapter(this) {

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // TODO: 2021/3/4 根据位置返回fragment
            switch (position){
                case 0: //返回题库列表fragment
                    return new ;
                case 1: //返回主页fragment
                    return new ;
                default: throw new IllegalArgumentException("position can't be this value:" + position);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    };

    @Override
    public void initView() {
        binding.mainViewPager.setUserInputEnabled(false);
        binding.mainViewPager.setAdapter(adapter);
        binding.mainViewPager.setCurrentItem(0, false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void initAction() {
        binding.mainNavBottom.setOnNavigationItemSelectedListener(item -> {
            final int position;
            switch (item.getItemId()) {
                case R.id.item_questions: position = 0; break;
                case R.id.item_profile: position = 1; break;
                default: throw new IllegalArgumentException("there's not such menu item");
            }
            binding.mainViewPager.setCurrentItem(position, false);
            return true;
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

}