package com.demo.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.demo.bean.CourseScore;

/**
 *@author: cjl
 *@desc:
 */
@Entity(tableName = "student") //设置表名
public class Student {

    @NonNull //主键必须添加这个注解
    @PrimaryKey(autoGenerate = true) //主键是否自动增长，默认为false
    public int id;

    @ColumnInfo
    public int age;

    @ColumnInfo(name = "name") //可以通过设置name =xxx 的方式重新命名字段
    public String studentName;

    @Embedded
    public CourseScore course;


    public Student() {
    }


    @Ignore //只允许有一个主构造方法，其他构造方法要使用@Ignore设置为忽略
    public Student(int age, String name, CourseScore course) {
        this.age = age;
        this.studentName = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", age=" + age + ", studentName='" + studentName + '\'' + ", course=" + course + '}';
    }
}
