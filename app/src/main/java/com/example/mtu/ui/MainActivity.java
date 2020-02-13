package com.example.mtu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mtu.R;
import com.example.mtu.adapters.ListViewAdapter;
import com.example.mtu.adapters.RecycleViewBaseAdapter;
import com.example.mtu.entity.ItemEntity;
import com.example.mtu.spider.contains.UrlContains;
import com.example.mtu.spider.parser.ListParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;
    private RecycleViewBaseAdapter mAdapter;
    private LinearLayout mListLoading;
    private List<ItemEntity> listData;
    private int SHOW_List = 1;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            mListLoading.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, "加载数据成功", Toast.LENGTH_SHORT).show();
            //通知数据已经改变
            mAdapter.updateData(listData);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("列表页");
        //找到控件
        mList = this.findViewById(R.id.recycleView);
        //1.初始化view
        initView();
        //2.初始化数据
        initData();


    }

    private void initView() {
        mListLoading = findViewById(R.id.list_loading);
        mListLoading.setVisibility(View.VISIBLE);

        listData = new ArrayList<>();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(linearLayoutManager);
        //创建适配器
        mAdapter = new ListViewAdapter(listData);
        //设置到RecycleView中
        mList.setAdapter(mAdapter);
        //4.设置监听事件
        initListener();
    }

    private void initListener() {
        mAdapter.setItemOnclickListener(position -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("detailUrl", listData.get(position).getDetailUrl());
            startActivity(intent);
        });
    }


    /**
     * 准备数据
     */
    private void initData() {
        //套路写法 list<> ->Adapter ->setAdapter ->显示数据

        new Thread(() -> {
            listData = ListParser.getListData(UrlContains.LIST_GUOCHAN_BASE_URL, 1);
            //更新界面，把数据显示到UI上
            Message message = Message.obtain();
            message.what = SHOW_List;
            mHandler.sendMessage(message);
        }).start();


    }


    /**
     * 菜单页
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单页点击效果
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //如果有多个二级菜单那种，应该把每一项的子项写在一起哟
        switch (item.getItemId()) {
            case R.id.guochan:
                Toast.makeText(MainActivity.this, "正在做别催了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rihan:
                Toast.makeText(MainActivity.this, "你想看什么？", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemTest_first:
                Toast.makeText(MainActivity.this, "别点啦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemTest_second:
                Toast.makeText(MainActivity.this, "再点打你哟", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "这是一个有营养的软件", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}