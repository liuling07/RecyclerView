package com.bbk.lling.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @Class: ListAdapter
 * @Description: 数据适配器
 * @author: lling(www.liuling123.com)
 * @Date: 2015/10/29
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public ListAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.mTextView.setText(mDatas.get(i));
        if(mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if(!itemViewHolder.itemView.hasOnClickListeners()) {
                Log.e("ListAdapter", "setOnClickListener");
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /**
         * 使用RecyclerView，ViewHolder是可以复用的。这根使用ListView的VIewHolder复用是一样的
         * ViewHolder创建的个数好像是可见item的个数+3
         */
        Log.e("ListAdapter", "onCreateViewHolder");
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(
                R.layout.item_layout, viewGroup, false));
        return holder;
    }

    /**
     * 向指定位置添加元素
     * @param position
     * @param value
     */
    public void add(int position, String value) {
        if(position > mDatas.size()) {
            position = mDatas.size();
        }
        if(position < 0) {
            position = 0;
        }
        mDatas.add(position, value);
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position);
    }

    /**
     * 移除指定位置元素
     * @param position
     * @return
     */
    public String remove(int position) {
        if(position > mDatas.size()-1) {
            return null;
        }
        String value = mDatas.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);
        }
    }

}
