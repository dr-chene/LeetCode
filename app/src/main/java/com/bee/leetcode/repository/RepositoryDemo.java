package com.bee.leetcode.repository;

import com.bee.leetcode.db.DaoDemo;
import com.bee.leetcode.db.bean.User;
import com.bee.leetcode.net.service.LoginServiceProxy;
import com.bee.leetcode.util.SpUtil;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例repository部分
 * 推荐做法：将LocalOption，RemoteOption保存在本地，需要改变网络请求等时改变其属性（setXXXXXX）
 * 再通过request上传，这样能减少对象的创建(因为大多数的回调接口是确定就不会改变的)
 */
public class RepositoryDemo {
    private final LoginServiceProxy api;
    private final DaoDemo local;
    private final DataRequest.LocalOption<User> localOption = new DataRequest.LocalOption<>();
    private final DataRequest.RemoteOption<User, User> remoteOption = new DataRequest.RemoteOption<>();

    public RepositoryDemo(DaoDemo l) {
        api = new LoginServiceProxy();
        this.local = l;
        localOption.setLocalGet(local.get()).setLocalSave(local::save)
                .setOtherWaySaveListener(data -> {
                    SpUtil.getInstance().putString("otherWayToSave", data.toString());
                    return true;
                });
        remoteOption.setTransfer(t -> t).setRemoteResultFailedListener((code, message) -> {
            //网络获取数据失败回调，强烈建议设置此回调
        });
    }

    public void login(String account, String password, DataRequest.RequestSuccess<User> success) {
        remoteOption.setRemoteGet(api.login(account, password, "email"));
        DataRequest.request(remoteOption, success);
    }

    public void load(DataRequest.RequestSuccess<User> success) {
        DataRequest.request(remoteOption, localOption, success);
    }

}
