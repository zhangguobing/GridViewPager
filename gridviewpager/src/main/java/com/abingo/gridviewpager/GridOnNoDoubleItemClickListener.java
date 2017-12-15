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

package com.abingo.gridviewpager;

import android.view.View;
import android.widget.AdapterView;

/**
 * 作者:章国兵
 * 创建时间: 2017/12/14 上午09:43
 * 描述:防止重复点击的监听器
 */
public abstract class GridOnNoDoubleItemClickListener implements AdapterView.OnItemClickListener {
    private int mThrottleFirstTime = 600;
    private long mLastClickTime = 0;

    public GridOnNoDoubleItemClickListener() {
    }

    public GridOnNoDoubleItemClickListener(int throttleFirstTime) {
        mThrottleFirstTime = throttleFirstTime;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime > mThrottleFirstTime) {
            mLastClickTime = currentTime;
            onNoDoubleClick(parent, view, position, id);
        }
    }

    public abstract void onNoDoubleClick(AdapterView<?> parent, View view, int position, long id);
}
