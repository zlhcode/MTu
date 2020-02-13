package com.example.mtu.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtu.R;
import com.example.mtu.entity.ItemEntity;
import com.example.mtu.view.SmartImageView;

import java.util.List;

public abstract class RecycleViewBaseAdapter extends RecyclerView.Adapter<RecycleViewBaseAdapter.InnerHolder> {

    private final List<ItemEntity> itemEntities;
    private OnItemClickListener mOnItemClickListener;

    public RecycleViewBaseAdapter(List<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }

    /**
     * 该方法用于创建条目的view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new InnerHolder(view);
    }

    //交给子类实现
    protected abstract View getSubView(ViewGroup parent, int viewType);

    /**
     * 这个方法用来绑定holder，一般用来设置数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置每一条的数据 取的下标就是position
        //设置数据的地方
        holder.setData(itemEntities.get(position), position);
    }

    public void updateData(List<ItemEntity> list) {// 更新数据方法
        itemEntities.clear();
        itemEntities.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (itemEntities != null) {
            return itemEntities.size();
        } else {
            return 0;
        }
    }

    public void setItemOnclickListener(OnItemClickListener listener) {
        //其实这里就是设置一个回调接口
        this.mOnItemClickListener = listener;
    }

    /**
     * 编写接口步骤
     * 1. 创建这个接口
     * 2. 定义该接口的方法
     * 3. 提供设置接口的方法，其实就是外部实现
     * 4. 接口方法调用
     */

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private final SmartImageView mImageSrc;
        private final TextView mTitle;
        private final TextView mTotalNum;
        private int position;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            //找到条目的控件
            mImageSrc = itemView.findViewById(R.id.list_imageSrc);
            mTitle = itemView.findViewById(R.id.list_title);
            mTotalNum = itemView.findViewById(R.id.list_totalNum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }

        public void setData(ItemEntity itemEntity, int position) {
            this.position = position;
            mTitle.setText(itemEntity.getTitle());
            mTotalNum.setText("总数：" + itemEntity.getTotalNum() + "张");
            mImageSrc.setImageUrlAndShow(itemEntity.getImageSrc());
        }
    }
}
