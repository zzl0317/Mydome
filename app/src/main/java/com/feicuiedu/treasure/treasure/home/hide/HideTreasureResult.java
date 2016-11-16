package com.feicuiedu.treasure.treasure.home.hide;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/15.
 */

public class HideTreasureResult {
    //    {
//        "errcode":0,                                    //返回值
//            "errmsg":"参数格式不正确!请检测传入参数格式"   //返回信息
//    }

    @SerializedName("errcode")
    public int code;

    @SerializedName("errmsg")
    public String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
