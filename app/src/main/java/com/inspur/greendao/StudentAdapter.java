package com.inspur.greendao;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {

    public StudentAdapter( @Nullable List<Student> data) {
        super(R.layout.item_student, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.id,item.getId()+"");
        helper.setText(R.id.sid,item.getSid());
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.sex,item.getSex());
    }

}
