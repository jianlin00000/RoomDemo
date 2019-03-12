package com.demo.ui.recyclerview.base;

import android.support.v4.util.SparseArrayCompat;


/**
 * 针对不同ItemViewDelegate进行类型管理，类似于代理
 */
public class ItemViewDelegateManager<T> {
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    /**
     * 获取类型数量
     * @return
     */
    public int getItemViewDelegateCount() {
        return delegates.size();
    }

    /**
     * 添加一种条目类型
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    /**
     * 添加一种条目类型，并制定type
     * @param viewType
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (delegates.get(viewType) != null) {//判断该指定的类型是否已经被使用了
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = " + viewType + ". Already registered ItemViewDelegate is " + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    /**
     * 移除某一类型条目
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 移除某一类型条目
     * @param itemType
     * @return
     */
    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 获取指定position的条目type
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    /**
     * 数据绑定
     * @param holder
     * @param item
     * @param position
     */
    public void convert(CommonViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {//类型符合，进行相应的数据操作
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    /**
     * 根据type获取ItemViewDelegate
     * @param viewType
     * @return
     */
    public ItemViewDelegate getItemViewDelegate(int viewType) {
        return delegates.get(viewType);
    }

    /**
     * 根据type获取对应条目的布局id
     * @param viewType
     * @return
     */
    public int getItemViewLayoutId(int viewType) {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    /**
     * 根据ItemViewDelegate获取其定义的type
     * @param itemViewDelegate
     * @return
     */
    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
