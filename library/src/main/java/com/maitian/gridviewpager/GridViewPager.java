/*
 * Copyright (C) 2016 zhangguobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maitian.gridviewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:章国兵
 * 创建时间: 2017/12/14 上午09:43
 * 描述:GridViewPager库的入口类
 */
public class GridViewPager extends LinearLayout {

    /**
     * 每页默认显示的列数
     */
    private static final int DEFAULT_PAGE_COLUMNS = 5;
    /**
     * 每页默认显示的行数
     */
    private static final int DEFAULT_PAGE_ROWS = 2;
    /**
     * 默认显示第几页
     */
    private static final int DEFAULT_CUR_INDEX = 0;

    private LayoutInflater inflater;
    private Context mContext;
    private ViewPager mPager;
    private PageIndicatorView mPageIndicatorView;
    private List<GridItem> mData;

    private List<GridView> mPagerList;
    private GridItemClickListener gridItemClickListener;
    private GridItemLongClickListener gridItemLongClickListener;

    /**
     * 总的页数 计算得出
     */
    private int pageCount;

    /**
     * 每一页显示的列数
     */
    private int pageColumns = DEFAULT_PAGE_COLUMNS;

    /**
     * 每一页显示的行数
     */
    private int pageRows = DEFAULT_PAGE_ROWS;

    /**
     * 当前显示的是第几页
     */
    private int curIndex = DEFAULT_CUR_INDEX;

    public GridViewPager(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public GridViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view, this);
        mPager = view.findViewById(R.id.viewpager);
        mPageIndicatorView = view.findViewById(R.id.pageIndicatorView);
    }

    /**
     * 使用数据源来初始化
     */
    public GridViewPager initWithData(List<GridItem> list) {
        mData = list;
        final int pageSize = pageColumns * pageRows;
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mData.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();

        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setNumColumns(pageColumns);
            gridView.setAdapter(new GridViewAdapter(mContext, mData, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new GridOnNoDoubleItemClickListener() {
                @Override
                public void onNoDoubleClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemClickListener == null){
                        return;
                    }
                    int position = pos + curIndex * pageSize;
                    gridItemClickListener.onClick(pos, position, mData.get(position).getName());
                }
            });
            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemLongClickListener == null) {
                        return false;
                     } else {
                        int position = pos + curIndex * pageSize;
                        gridItemLongClickListener.onLongClick(pos, position, mData.get(position).getName());
                        return true;
                    }
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter<>(mPagerList));

        mPageIndicatorView.setSelectedColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        mPageIndicatorView.setUnselectedColor(Color.parseColor("#D4D3D3"));
        mPageIndicatorView.setViewPager(mPager);
        return this;
    }

    /**
     * optional 设置单元点击事件
     *
     * @param listener
     * @return
     */
    public GridViewPager setGridItemClickListener(GridItemClickListener listener) {
        gridItemClickListener = listener;
        return this;
    }

    /**
     * optional 设置单元长按事件
     *
     * @param listener
     * @return
     */
    public GridViewPager setGridItemLongClickListener(GridItemLongClickListener listener) {
        gridItemLongClickListener = listener;
        return this;
    }

    public List<GridView> getPagerList() {
        return mPagerList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageColumns() {
        return pageColumns;
    }

    public GridViewPager setPageColumns(int pageColumns) {
        this.pageColumns = pageColumns;
        return this;
    }

    public int getPageRows() {
        return pageRows;
    }

    public GridViewPager setPageRows(int pageRows) {
        this.pageRows = pageRows;
        return this;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public PageIndicatorView getPageIndicatorView() {
        return mPageIndicatorView;
    }
}
