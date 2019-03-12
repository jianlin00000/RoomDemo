package com.demo.ui.adapter;

import android.content.Context;

import com.demo.db.model.Student;
import com.demo.ui.R;
import com.demo.ui.recyclerview.CommonAdapter;
import com.demo.ui.recyclerview.base.CommonViewHolder;

import java.util.List;

/**
 *@author: cjl
 *@date: 2019/3/12 15:43
 *@desc:
 */
public class StudentAdapter extends CommonAdapter<Student>{

    /**
     *  @param context
     * @param list  数据集合
     * @param layoutId  条目布局id
     */
    public StudentAdapter(Context context, List<Student> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Student student, int position) {
        holder.setText(R.id.tv_id,student.id+"");
        holder.setText(R.id.tv_name,student.studentName);
        holder.setText(R.id.tv_age,student.age+"");
        holder.setText(R.id.tv_chinese,student.course.chinese+"");
        holder.setText(R.id.tv_math,student.course.math+"");
        holder.setText(R.id.tv_english,student.course.english+"");
    }
}
