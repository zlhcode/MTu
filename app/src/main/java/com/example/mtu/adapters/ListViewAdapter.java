package com.example.mtu.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.mtu.R;
import com.example.mtu.entity.ItemEntity;

import java.util.List;

/**
 * 列表适配器
 */
public class ListViewAdapter extends RecycleViewBaseAdapter {


    public ListViewAdapter(List<ItemEntity> itemEntities) {
        super(itemEntities);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        return View.inflate(parent.getContext(), R.layout.item_list_view, null);
    }


}
