package com.feicuiedu.treasure.user.account;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface AccoutView {
    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    /** 更新头像*/
    void updatePhoto(String url);
}
