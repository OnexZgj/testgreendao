package com.inspur.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inspur.greendao.database.DaoMaster;
import com.inspur.greendao.database.DaoSession;

public class App extends Application {
    private App app;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initDatabase();
    }

    /**
     * 配置数据库及初始化
     */
    private void initDatabase() {
        //创建数据库mydb.db
        OnexSQLiteOpenHelper helper = new OnexSQLiteOpenHelper(this, "onexzgj.db",
                null);
//        //获取可写数据库
//        SQLiteDatabase database = helper.getWritableDatabase();
//        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(database);

        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        //获取Dao对象管理者
        mDaoSession = daoMaster.newSession();


//MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true


    }


    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public App getInstance() {
        return app;
    }


}
