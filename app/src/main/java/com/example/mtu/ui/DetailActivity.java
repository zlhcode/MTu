package com.example.mtu.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtu.R;
import com.example.mtu.adapters.DetailViewAdapter;
import com.example.mtu.entity.DetailEntity;
import com.example.mtu.spider.parser.DetailParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情
 */
public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "detail";
    private String mDetailUrl;
    private LinearLayout mLoading;
    private DetailEntity detailEntity;
    private DetailViewAdapter mDetailViewAdapter;
    private RecyclerView mDetailImage;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(DetailActivity.this, "数据加载成功", Toast.LENGTH_SHORT).show();
            mDetailViewAdapter.updateData(mImagesList);
            mLoading.setVisibility(View.INVISIBLE);
        }
    };
    private List<String> mImagesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailUrl = getIntent().getStringExtra("detailUrl");
        Log.d(TAG, "onCreate: 获取到的detailUrl:" + mDetailUrl);
        setContentView(R.layout.activity_detail);
        setTitle("是图片啦");
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        new Thread(() -> {
            detailEntity = DetailParser.parseOneDetail(mDetailUrl);
            mImagesList = detailEntity.getImagesList();
            Message message = Message.obtain();
            message.what = 1;
            mHandler.sendMessage(message);
        }).start();
        //初始化适配器
        mDetailViewAdapter = new DetailViewAdapter(mImagesList);

        //设置适配器
        mDetailImage.setAdapter(mDetailViewAdapter);
    }

    /**
     * 初始化布局
     */
    private void initView() {

        mLoading = findViewById(R.id.detail_loading);
        mLoading.setVisibility(View.VISIBLE);

        mDetailImage = findViewById(R.id.detailRecycleView);

        detailEntity = new DetailEntity();
        mImagesList = new ArrayList<>();
        detailEntity.setImagesList(mImagesList);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mDetailImage.setLayoutManager(linearLayoutManager);

    }


}
