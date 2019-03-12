package com.demo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.demo.bean.CourseScore;
import com.demo.db.DbManager;
import com.demo.db.model.Student;
import com.demo.ui.adapter.StudentAdapter;
import com.demo.ui.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity
        extends AppCompatActivity {
    public static final String TAG=MainActivity.class.getSimpleName();
    @BindView(R.id.rv) RecyclerView mRv;
    private            Unbinder     mUnbinder;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private DbManager      mDbManager;
    private List<Student>  mList;
    private EmptyWrapper   mEmptyWrapper;
    private StudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        mUnbinder = ButterKnife.bind(this);
        mDbManager = DbManager.getInstance();
        mList = new ArrayList<>();
        mAdapter = new StudentAdapter(this, mList, R.layout.layout_list_item);
        mEmptyWrapper = new EmptyWrapper(mAdapter);
        mEmptyWrapper.setEmptyView(R.layout.empty_view);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mEmptyWrapper);


        //与Rxjava中的Flowable结合监听数据变化
        mDisposable.add(mDbManager.getAll().subscribe(students -> {
            Log.i(TAG, "getAll():"+students.toString());
            mList.clear();
            mList.addAll(students);
            mEmptyWrapper.notifyDataSetChanged();
        }));

        mAdapter.setOnItemClickListener((view, holder, position) -> {
            Student student = mList.get(position);
            student.course.english=0;
            mDisposable.add(mDbManager.update(student)
                                      .subscribe(integer -> {

                                      }));
        });

        mAdapter.setOnItemLongClickListener((view, holder, position) -> {
            Student student = mList.get(position);
            mDisposable.add(mDbManager.delete(student)
                                      .subscribe(integer -> {

                                      }));
            return true;
        });
    }


    @OnClick({R.id.bt_insert, R.id.bt_insert_one, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_insert:
                mDisposable.add(mDbManager.insert(getTestData()).subscribe(longs -> {

                }));
                break;
            case R.id.bt_insert_one:
                mDisposable.add(mDbManager.insert(getStudent(new Random())).subscribe(aLong -> {

                                          }));
                break;
            case R.id.bt_delete:
                mDisposable.add(mDbManager.deleteAll().subscribe(integer -> {

                }));
                break;
        }
    }

    /**
     * 获取测试数据
     * @return
     */
    private List<Student> getTestData() {
        List<Student> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            list.add(getStudent(random));
        }
        return list;
    }

    @NonNull
    private Student getStudent(Random random) {
        int chinese = random.nextInt(150);
        int english = random.nextInt(150);
        int math = random.nextInt(150);
        int sport = random.nextInt(100);
        CourseScore score = new CourseScore(chinese, english, math);
        String[] names = {"张三", "李四", "王五", "赵日天", "张长弓", "奥巴马"};
        int age = random.nextInt(10) + 8;
        String name = names[random.nextInt(names.length - 1)];
        return new Student(age, name, score);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


}
