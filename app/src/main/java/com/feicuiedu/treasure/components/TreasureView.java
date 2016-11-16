package com.feicuiedu.treasure.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.feicuiedu.treasure.R;
import com.feicuiedu.treasure.treasure.Treasure;
import com.feicuiedu.treasure.treasure.home.map.MapFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 宝藏卡片视图
 */
public class TreasureView extends RelativeLayout{

    public TreasureView(Context context) {
        super(context);
        init();
    }

    public TreasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TreasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 用来显示宝藏title
    @BindView(R.id.tv_treasureTitle) TextView tvTitle;
    // 用来显示宝藏位置描述
    @BindView(R.id.tv_treasureLocation)TextView tvLocation;
    // 用来显示宝藏距离
    @BindView(R.id.tv_distance)TextView tv_Distance;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_treasure,this,true);
        ButterKnife.bind(this);
    }
    public void bindTreasure(@NonNull Treasure treasure){
        //可以去完成宝藏标题等的展示
        tvTitle.setText(treasure.getTitle());//宝藏的标题
        tvLocation.setText(treasure.getLocation());//宝藏的地址
        //计算距离我们有多远
        double distance=0.00d;
        LatLng Mylocation= MapFragment.getMyLocation();//当到我们的位置
        if(Mylocation==null){
            distance=0.00d;
        }
        LatLng target=new LatLng(treasure.getLatitude(),treasure.getLongitude());
        //得到我们离宝藏的距离
        distance=DistanceUtil.getDistance(target,Mylocation);
        DecimalFormat decimalFormat=new DecimalFormat("00.00");
        String text=decimalFormat.format(distance/1000)+"km";
        tv_Distance.setText(text);
    }

}
