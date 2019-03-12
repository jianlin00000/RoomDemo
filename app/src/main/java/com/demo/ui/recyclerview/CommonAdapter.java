package com.demo.ui.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.ui.recyclerview.base.CommonViewHolder;

import java.util.List;


/**
 *@author: cjl
 *@date: 2018/6/28 19:25
 *@desc: RecyclerView通用Adapter
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder>{
    protected Context           mContext;
    protected List<T>           mDatas; //要设置的数据集合
    protected LayoutInflater    mInflater;
    protected int               mLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    /**
     *
     * @param context
     * @param list  数据集合
     * @param layoutId  条目布局id
     */
    public CommonAdapter(Context context, List<T> list, int layoutId) {
        mContext = context;
        mDatas = list;
        mLayoutId = layoutId;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommonViewHolder holder = CommonViewHolder.createViewHolder(mContext, parent, mLayoutId);
        setListener(parent, holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(CommonViewHolder holder, T t,int position);

    /**
     * 插入一条数据
     * @param t
     * @param position
     */
    public void insert(T t,int position){
        mDatas.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 删除某一个position的数据
     * @param position
     */
    public void remove(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void setData(List<T> list){
        mDatas = list;
    }


    /**
     * 事件监听
     * @param parent
     * @param viewHolder
     */
    protected void setListener(final ViewGroup parent, final CommonViewHolder viewHolder) {
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder , position);
                }
            }
        });

        viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemLongClickListener.onItemLongClick(v, viewHolder, position);
                }
                return true;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}
