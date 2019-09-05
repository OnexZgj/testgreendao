package com.inspur.greendao;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.inspur.greendao.circleview.CircleViewActivity;
import com.inspur.greendao.database.StudentDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.btn_view)
    Button btnView;

    @BindView(R.id.btn_replace)
    Button btnReplace;
    @BindView(R.id.btn_count)
    Button btnCount;
    @BindView(R.id.rv_am_info)
    RecyclerView rvAmInfo;
    private ArrayList<Student> studentsdatas;
    private StudentAdapter studentAdapter;
    private List<Student> students = new ArrayList<>();
    private ArrayList<Schoolmaster> schoolmasters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        studentsdatas = new ArrayList<>();


        schoolmasters = new ArrayList<>();

        for (int i = 10; i < 50; i++) {
            studentsdatas.add(new Student((long) i, "" + i, "Onex_" + i, "男"));
        }


        for (int j=1;j<30;j++){
            Schoolmaster schoolmaster = new Schoolmaster((long) j, "" + j, "school" + j, j + 30);
            schoolmasters.add(schoolmaster);
        }

        App.getmDaoSession().getSchoolmasterDao().insertInTx(schoolmasters);



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }


        rvAmInfo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        studentAdapter = new StudentAdapter(students);
        rvAmInfo.setAdapter(studentAdapter);


        long count = App.getmDaoSession().getSchoolmasterDao().count();


        showToast(count+"");

    }

    @OnClick({R.id.btn_view,R.id.btn_count,R.id.btn_replace, R.id.btn_add, R.id.btn_delete, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_view:

                startActivity(new Intent(MainActivity.this, CircleViewActivity.class));

                break;
            case R.id.btn_add:

                Student student = new Student((long) 1, "001", "onex", "男");
                App.getmDaoSession().getStudentDao().insert(student);
                showToast("添加成功");

                App.getmDaoSession().getStudentDao().insertInTx(studentsdatas);

                break;
            case R.id.btn_delete:

                List<Student> onex2 = App.getmDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.Name.eq("onex")).list();

                showToast(onex2.get(0).getSex());


                App.getmDaoSession().getStudentDao().delete(onex2.get(0));

                showToast("删除成功");

                break;
            case R.id.btn_update:

                List<Student> onex1 = App.getmDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.Name.eq("onex")).list();

                showToast(onex1.get(0).getSex());
                if (onex1 != null && onex1.size() > 0) {
                    students.add(onex1.get(0));
                    studentAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.btn_query:
                /**
                 * whereOr语句相当于select *from where name like ? or name = ? or age>?
                 * ge是 >= 、like 则是包含的意思
                 * whereOr是或的意思，比如下面的whereOr的意思就是age>=22||name 包含 张三
                 * where则是age>=22 && name 包含 张三
                 *greenDao除了ge和like操作之外还有很多其他方法
                 * eq == 、 noteq != 、  gt >  、lt <  、le  <=  、between 俩者之间
                 * in  在某个值内   、notIn  不在某个值内
                 */

                students = App.getmDaoSession().getStudentDao().loadAll();


                studentAdapter.setNewData(students);

                break;
            case R.id.btn_replace:
                Student student2 = new Student((long) 1, "002", "onex2", "女");
                App.getmDaoSession().getStudentDao().update(student2);
                showToast("替换成功");
                break;
            case R.id.btn_count:

                long count = App.getmDaoSession().getStudentDao().count();

                showToast(count+"");

                List<Student> list = App.getmDaoSession().getStudentDao().queryBuilder().limit(10).list();

                studentAdapter.setNewData(list);

                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
