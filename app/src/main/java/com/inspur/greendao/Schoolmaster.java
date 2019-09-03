package com.inspur.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Schoolmaster {

    @Id
    private Long id;
    private String sid;
    private String name;
    private int age;
    @Generated(hash = 1994554973)
    public Schoolmaster(Long id, String sid, String name, int age) {
        this.id = id;
        this.sid = sid;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 911259452)
    public Schoolmaster() {
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
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    

}
