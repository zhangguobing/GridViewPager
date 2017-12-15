# GridViewPager
ViewPager+GridView实现首页导航栏布局分页效果
![](https://github.com/zhangguobing/GridViewPager/blob/master/20171214_160600.jpg?raw=true)
###添加依赖
```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://dl.bintray.com/zhangguobing1222/maven"
        }
    }
}
```
* Gradle
```
  compile 'com.abingo:gridviewpager:1.0.0'
```
* Maven
```
<dependency>
  <groupId>com.abingo</groupId>
  <artifactId>gridviewpager</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
###使用：
```
//使用builder模式设计初始化
mGridViewPager
    //设置每页显示的列数
    .setPageColumns(5)
    //设置每页显示的行数
    .setPageRows(2)
    .setImageSetListener(new ImageSetListener() {
        @Override
        public void setImage(ImageView iv, int pos) {
            //图片加载方式任选
            iv.setBackgroundResource(mImgRes.get(pos));
        }
    })
    .setGridItemClickListener(new GridItemClickListener() {
        @Override
        public void onClick(int pos, int position, String str) {
            Log.d("onClick", pos + "/" + str);
        }
    })
    .setGridItemLongClickListener(new GridItemLongClickListener() {
        @Override
        public void onClick(int pos, int position, String str) {
            Log.d("longClick", pos + "/" + str);
        }
    })
    //传入String的List 必须作为最后一步
    .initWithData(initData());
```


## License

    Copyright 2017 zhangguobing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.