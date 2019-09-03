package com.inspur.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.inspur.greendao.database.DaoMaster;
import com.inspur.greendao.database.SchoolmasterDao;
import com.inspur.greendao.database.StudentDao;
import com.inspur.greendao.database.TeacherDao;

import org.greenrobot.greendao.database.Database;

public class OnexSQLiteOpenHelper extends DaoMaster.OpenHelper {
    public OnexSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, StudentDao.class, TeacherDao.class, SchoolmasterDao.class);
    }
}