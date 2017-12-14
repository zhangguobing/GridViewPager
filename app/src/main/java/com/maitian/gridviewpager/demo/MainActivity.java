package com.maitian.gridviewpager.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.maitian.gridviewpager.GridItem;
import com.maitian.gridviewpager.GridItemClickListener;
import com.maitian.gridviewpager.GridItemLongClickListener;
import com.maitian.gridviewpager.GridViewPager;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 章国兵
 * 创建时间: 2017/12/13 下午10:47
 * 描述:高度填满内容的GridView
 */
public class MainActivity extends AppCompatActivity {

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridViewPager mGridViewPager = findViewById(R.id.mGridViewPager);
        //初始化GridViewPager
        mGridViewPager
                .setPageColumns(5)
                .setPageRows(2)
                .setGridItemClickListener(new GridItemClickListener() {
                    @Override
                    public void onClick(int pos, int position, String str) {
                        Toast.makeText(MainActivity.this,"position=" + position + ",title=" + str, Toast.LENGTH_SHORT).show();
                    }
                })
                .setGridItemLongClickListener(new GridItemLongClickListener() {
                    @Override
                    public void onLongClick(int pos, int position, String str) {
                        Toast.makeText(MainActivity.this,"position=" + position + ",title=" + str, Toast.LENGTH_SHORT).show();
                    }
                })
                .initWithData(getData());

//        mGridViewPager.getPageIndicatorView().setAnimationType(AnimationType.SCALE);
    }

    /**
     * 获取数据源
     */
    private List<GridItem> getData() {
        List<GridItem> mData = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            mData.add(new GridItem(titles[i], imageId));
        }
        return mData;
    }
}
