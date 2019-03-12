package com.demo.db;

import com.demo.db.model.Student;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *@author: cjl
 *@desc: 数据库操作工具类
 */
public class DbManager {
    private DbManager() {}

    public static DbManager getInstance(){
        return Holder.instance;
    }


    private static class Holder{
        private static final DbManager instance =new DbManager();
    }


    /**
     * 插入数据
     * @param student
     */
    public Observable<Long> insert(Student student){
        return Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            Long insert = AppDatabase.getDatabase().getStudentDao().insert(student);
            emitter.onNext(insert);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 插入数据
     * @param list
     * @return
     */
    public Observable<List<Long>> insert(List<Student> list){
        return Observable.create((ObservableOnSubscribe<List<Long>>) emitter -> {
            List<Long> inserts = AppDatabase.getDatabase().getStudentDao().insert(list);
            emitter.onNext(inserts);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 更新
     * @param student
     * @return
     */
    public Observable<Integer> update(Student student){
        return Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            int inserts = AppDatabase.getDatabase().getStudentDao().update(student);
            emitter.onNext(inserts);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 普通查询方式
     * @return
     */
    public Observable<List<Student>> loadAll(){
        return Observable.create((ObservableOnSubscribe<List<Student>>) emitter -> {
            List<Student> list = AppDatabase.getDatabase().getStudentDao().loadAll();
            emitter.onNext(list);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 获取所有数据
     * @return
     */
    public Flowable<List<Student>> getAll(){
        return AppDatabase.getDatabase()
                   .getStudentDao()
                   .getAll()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除某一个student
     * @param student
     * @return
     */
    public Observable<Integer> delete(Student student){
        return Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            int inserts = AppDatabase.getDatabase().getStudentDao().delete(student);
            emitter.onNext(inserts);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 删除所有数据
     * @return
     */
    public Observable<Integer> deleteAll(){
        return Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            int inserts = AppDatabase.getDatabase().getStudentDao().deleteAll();
            emitter.onNext(inserts);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }

}
