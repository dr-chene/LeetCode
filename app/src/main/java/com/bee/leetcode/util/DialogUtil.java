package com.bee.leetcode.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

public class DialogUtil {

    private DialogUtil() {
    }

    /**
     * @param context          上下文
     * @param title            dialog的标题
     * @param message          dialog的内容
     * @param positiveBtnName  按钮的内容
     * @param positiveListener positive按钮的监听
     * @param negativeBtnName  negative按钮的内容
     * @param negativeListener negative按钮的监听
     * @return
     */
    public static Dialog getDialog(Context context, String title, String message, String positiveBtnName, DialogInterface.OnClickListener positiveListener, String negativeBtnName, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtnName, positiveListener)
                .setNegativeButton(negativeBtnName, negativeListener)
                .create();
    }

    /**
     * 自动添加一个确定的按钮 点击确定后对话框消失
     *
     * @param context 上下文
     * @param title   dialog的标题
     * @param message dialog的内容
     * @return
     */
    public static Dialog getDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", ((dialog, which) -> dialog.dismiss()))
                .create();
    }

    /**
     * 返回一个加载中的对话框
     *
     * @param context 上下文
     * @param message 加载时显示的内容
     * @return
     */
    public static ProgressDialog getProgressDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        return dialog;
    }

    /**
     * 返回一个单选框的Dialog，自动添加取消按钮
     *
     * @param context          上下文
     * @param title            标题
     * @param array            单选框的内容
     * @param index            默认选中
     * @param onClickListener  单选框点击事件
     * @param positiveListener 确定按钮点击事件
     * @return
     */
    public static Dialog getSingleChoiceDialog(Context context, String title, String[] array, int index, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setSingleChoiceItems(array, index, onClickListener)
                .setTitle(title)
                .setPositiveButton("确定", positiveListener)
                .setNegativeButton("取消", ((dialog, which) -> dialog.dismiss()));
        return builder.create();
    }

    /**
     * 返回一个多选框的Dialog，自动添加取消按钮
     *
     * @param context          上下文
     * @param title            标题
     * @param array            多选框的内容
     * @param listener         多选框点击事件
     * @param positiveListener 确定按钮点击事件
     * @return
     */
    public static Dialog getMultiChoiceDialog(Context context, String title, String[] array, DialogInterface.OnMultiChoiceClickListener listener, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMultiChoiceItems(array, null, listener)
                .setTitle(title)
                .setPositiveButton("确定", positiveListener)
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    public static Builder builder(Activity activity) {
        return new Builder(activity);
    }

    static class Builder {

        private final AlertDialog.Builder builder;
        private DialogInterface.OnClickListener positiveListner;
        private DialogInterface.OnClickListener negativeListner;
        private String positiveText = "确定";
        private String negativeText = "取消";

        private Builder(Context context) {
            builder = new AlertDialog.Builder(context);
            positiveListner = (dialog, which) -> dialog.dismiss(); //默认的监听
            negativeListner = (dialog, which) -> dialog.dismiss(); //默认的监听
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public Builder view(int resId) {
            builder.setView(resId);
            return this;
        }

        public Builder view(View view) {
            builder.setView(view);
            return this;
        }

        public Builder setText(String text) {
            builder.setMessage(text);
            return this;
        }

        public Builder setTitle(String text) {
            builder.setTitle(text);
            return this;
        }

        public Builder icon(int id) {
            builder.setIcon(id);
            return this;
        }

        public Builder icon(Drawable drawable) {
            builder.setIcon(drawable);
            return this;
        }

        public Builder positiveButton(String text, DialogInterface.OnClickListener listener) {
            positiveText = text;
            positiveListner = listener;
            return this;
        }

        public Builder negativeButton(String text, DialogInterface.OnClickListener listener) {
            negativeText = text;
            negativeListner = listener;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            builder.setCancelable(cancelable);
            return this;
        }

        public Builder singleChoiceItems(String[] array, int index, DialogInterface.OnClickListener listener) {
            builder.setSingleChoiceItems(array, index, listener);
            return this;
        }

        public Builder multiChoiceItems(String[] array, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
            builder.setMultiChoiceItems(array, checkedItems, listener);
            return this;
        }

        public Dialog build() {
            return builder.setPositiveButton(positiveText, positiveListner)
                    .setNegativeButton(negativeText, negativeListner)
                    .create();
        }
    }
}

