package com.demo.bean;

import android.arch.persistence.room.Ignore;

/**
 *@author: cjl
 *@desc:
 */
public class CourseScore {

    public int chinese;
    public int english;
    public int math;
    @Ignore
    public int sports;  //这个字段将被忽略，不会被映射到表中

    public CourseScore(int chinese, int english, int math, int sports) {
        this.chinese = chinese;
        this.english = english;
        this.math = math;
        this.sports = sports;
    }

    public CourseScore(int chinese, int english, int math) {
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }

    @Override
    public String toString() {
        return "CourseScore{" + "chinese=" + chinese + ", english=" + english + ", math=" + math + ", sports=" + sports + '}';
    }
}
