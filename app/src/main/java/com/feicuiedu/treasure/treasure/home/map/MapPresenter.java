package com.feicuiedu.treasure.treasure.home.map;

/**
 * Created by Administrator on 2016/11/11.
 */

import com.feicuiedu.treasure.net.NetClient;
import com.feicuiedu.treasure.treasure.Area;
import com.feicuiedu.treasure.treasure.Treasure;
import com.feicuiedu.treasure.treasure.TreasureApi;
import com.feicuiedu.treasure.treasure.TreasureRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 业务操作的类
 */
public class MapPresenter {
    /**
     * 业务请求中视图都怎么变化呢？
     * 1. 显示信息
     * 2. 将我们获取到的宝藏数据给视图展示
     */
    private MapMvpView mapMvpView;
    public Area area;
    public MapPresenter(MapMvpView mapMvpView) {
        this.mapMvpView = mapMvpView;
    }
    public void getTreasure(Area area){
        if (TreasureRepo.getInstance().isCached(area)){
            return;
        }
        // 来去进行宝藏数据的获取
        this.area=area;
        TreasureApi treasureApi =NetClient.getInstance().getTreasureApi();
        Call<List<Treasure>> treasureCall = treasureApi.getTreasureArea(area);
        treasureCall.enqueue(callback);
    }
    private Callback<List<Treasure>> callback=new Callback<List<Treasure>>() {
        //请求成功的时候
        @Override
        public void onResponse(Call<List<Treasure>> call, Response<List<Treasure>> response ) {
            if(response.isSuccessful()){
                List<Treasure> treasureList=response.body();
                if (treasureList == null) {
                    mapMvpView.showMassage("发送了未知的错误");
                    return;
                }
                TreasureRepo.getInstance().addTreasure(treasureList);
                TreasureRepo.getInstance().cache(area);
                mapMvpView.setData(treasureList);
            }
        }
        //请求失败的时候
        @Override
        public void onFailure(Call<List<Treasure>> call, Throwable t) {
            mapMvpView.showMassage(t.getMessage());
        }
    };
}
