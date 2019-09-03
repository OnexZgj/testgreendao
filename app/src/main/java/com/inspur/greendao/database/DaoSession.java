package com.inspur.greendao.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.inspur.greendao.Student;
import com.inspur.greendao.Teacher;
import com.inspur.greendao.Schoolmaster;

import com.inspur.greendao.database.StudentDao;
import com.inspur.greendao.database.TeacherDao;
import com.inspur.greendao.database.SchoolmasterDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig teacherDaoConfig;
    private final DaoConfig schoolmasterDaoConfig;

    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final SchoolmasterDao schoolmasterDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        schoolmasterDaoConfig = daoConfigMap.get(SchoolmasterDao.class).clone();
        schoolmasterDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);
        schoolmasterDao = new SchoolmasterDao(schoolmasterDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(Teacher.class, teacherDao);
        registerDao(Schoolmaster.class, schoolmasterDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
        schoolmasterDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public SchoolmasterDao getSchoolmasterDao() {
        return schoolmasterDao;
    }

}
