package com.example.mtu.adapters;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtu.R;
import com.example.mtu.view.SmartImageView;

import java.util.List;


public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewAdapter.InnerHolder> {

    private static final String TAG = "detailViewAdapter";
    private List<String> imagesList;

    public DetailViewAdapter(List<String> imagesList) {
        this.imagesList = imagesList;
    }


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_detail_view, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(imagesList.get(position));
    }

    @Override
    public int getItemCount() {
        if (null != imagesList) {
            return imagesList.size();
        }
        return 0;
    }

    public void updateData(List<String> mImagesList) {
        Log.d(TAG, "updateData: " + mImagesList.size());
        imagesList.clear();
        imagesList.addAll(mImagesList);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private  SmartImageView mImageView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.detail_item_image);
        }

        public void setData(String imageSrc) {
            mImageView.setImageUrlAndShow(imageSrc);
        }
    }
}
