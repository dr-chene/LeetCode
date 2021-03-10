package com.bee.leetcode.repository;

import com.bee.leetcode.db.DaoDemo;
import com.bee.leetcode.net.BeanDemo;
import com.bee.leetcode.net.service.LoginServiceProxy;
import com.bee.leetcode.util.SpUtil;

/**
 * created by dr_chene on 2021/2/19
 * desc 网络框架示例repository部分
 */
public class RepositoryDemo {
    private final LoginServiceProxy api;
    private final DaoDemo local;
    private final DataRequest.LocalOption<BeanDemo> localOption = new DataRequest.LocalOption<>();
    private final DataRequest.RemoteOption<BeanDemo, BeanDemo> remoteOption = new DataRequest.RemoteOption<>();

    public RepositoryDemo(DaoDemo l) {
        api = new LoginServiceProxy();
        this.local = l;
        localOption.setLocalGet(local.get()).setLocalSave(local::save)
                .setOtherWaySaveListener(data -> {
                    SpUtil.getInstance().putString("otherWayToSave", data.toString());
                    return true;
                });
        remoteOption.setTransfer(t -> t);
    }

    public void login(String account, String password, DataRequest.RequestSuccess<BeanDemo> success) {
        remoteOption.setRemoteGet(api.login(account, password, "email"));
        DataRequest.request(remoteOption, success);
    }

    public void load(DataRequest.RequestSuccess<BeanDemo> success) {
        DataRequest.request(remoteOption, localOption, success);
    }

}
