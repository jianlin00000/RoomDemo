package com.demo.ui.recyclerview.base;

/**
 *@desc: 多类型条目需要实现的接口
 */
public interface ItemViewDelegate<T> {

    /**
     * 获取该类型条目的LayoutId
     * @return
     */
    int getItemViewLayoutId();

    /**
     *  用于类型条目判断
     * @param item
     * @param position
     * @return
     */
    boolean isForViewType(T item, int position);

    /**
     * 数据处理
     * @param holder
     * @param t
     * @param position
     */
    void convert(CommonViewHolder holder, T t, int position);

}
