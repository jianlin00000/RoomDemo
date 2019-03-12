package com.demo.ui.recyclerview.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 *@author: cjl
 *@date: 2018/6/28 18:53
 *@desc: RecyclerView通用ViewHolder
 */
public class CommonViewHolder
        extends RecyclerView.ViewHolder{
    private Context mContext;
    private SparseArray<View> mViews;
    private View  mConverntView;

    public CommonViewHolder(Context context,View itemView) {
        super(itemView);
        this.mContext=context;
        this.mConverntView=itemView;
    }

    public static CommonViewHolder createViewHolder(Context context,View itemView){
        return new CommonViewHolder(context, itemView);
    }

    public static CommonViewHolder createViewHolder(Context context,ViewGroup parent,@LayoutRes int layoutId){
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CommonViewHolder(context, view);
    }


    /**
     * 获取条目对应的View
     * @return
     */
    public View getItemView(){
        return mConverntView;
    }

    public CommonViewHolder setText(@IdRes int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setTextColor(@IdRes int viewId,@ColorRes int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(tv.getResources().getColor(color));
        return this;
    }

    public CommonViewHolder setImageResource(@IdRes int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setImageLevel(@IdRes int viewId, int level) {
        ImageView view = getView(viewId);
        view.setImageLevel(level);
        return this;
    }


    public CommonViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }


    public <T extends View> T getView(@IdRes int viewId){
        if (mViews==null) mViews=new SparseArray<>();
        View view = mViews.get(viewId);
        if (view==null){
            view=mConverntView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

}
