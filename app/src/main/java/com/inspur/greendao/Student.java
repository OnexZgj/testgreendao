package com.inspur.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Student {

    @Id
    private Long id;
    private String sid;
    private String name;
    private String sex;

    //数据库中忽略的字段
    @Transient
    private String hobby;

    @Generated(hash = 738883774)
    public Student(Long id, String sid, String name, String sex) {
        this.id = id;
        this.sid = sid;
        this.name = name;
        this.sex = sex;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
