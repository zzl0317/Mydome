package com.feicuiedu.treasure.treasure.home.detail;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public interface TreasureDetialView {

    // 视图的方法
    void showMessage(String msg);// 显示信息

    void setData(List<TreasureDetailResult> results);// 设置数据
}
