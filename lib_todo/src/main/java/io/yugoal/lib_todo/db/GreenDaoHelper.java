package io.yugoal.lib_todo.db;

import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import io.yugoal.lib_todo.app.ToDoHelper;
import io.yugoal.lib_todo.model.ToDoBean;
import io.yugoal.lib_todo.wantodo.db.DaoMaster;
import io.yugoal.lib_todo.wantodo.db.DaoSession;
import io.yugoal.lib_todo.wantodo.db.ToDoBeanDao;

/**
 * @user caoyu
 * @date 2019/11/14
 */
public class GreenDaoHelper {

    private static final String DB_BAME = "wantodo_db";
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase mDb;
    //管理数据库
    private static DaoMaster mDaoMaster;
    //管理各种实体Dao,不让业务层拿到session直接去操作数据库，统一由此类提供方法
    private static DaoSession mDaoSession;

    /**
     * 设置greenDao
     */
    public static void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(ToDoHelper.getContext(), DB_BAME, null);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    public static void addToDo( ToDoBean toDoBean) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.insert(toDoBean);
    }

    public static void deleteToDo(int id) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.delete(selectToDoBean(id));
    }


    public static void updataToDo(ToDoBean toDoBean) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.insertOrReplace(toDoBean);
    }

    public static List<ToDoBean> getToDoList(int status, int type, int priority, int orderby) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        List<ToDoBean> list = new ArrayList<>();
        QueryBuilder<ToDoBean> queryBuilder = dao.queryBuilder().where(
                ToDoBeanDao.Properties.Priority.eq(priority),
                ToDoBeanDao.Properties.Type.eq(type),
                ToDoBeanDao.Properties.Status.eq(status)
        );
        switch (orderby) {
            case 1://完成日期顺序
                list = queryBuilder.orderAsc(ToDoBeanDao.Properties.CompleteDate).list();
                break;
            case 2://完成日期逆序
                list = queryBuilder.orderDesc(ToDoBeanDao.Properties.CompleteDate).list();
                break;
            case 3://创建日期顺序
                list = queryBuilder.orderAsc(ToDoBeanDao.Properties.Date).list();
                break;
            default://创建日期逆序(默认)
                list = queryBuilder.orderDesc(ToDoBeanDao.Properties.Date).list();
                break;
        }
        return list;
    }

    public static void completedToDo(int id, int status) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        ToDoBean toDoBean = selectToDoBean(id);
        toDoBean.setStatus(status);
        dao.insertOrReplace(toDoBean);
    }

    public static ToDoBean selectToDoBean(int id) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        return dao.queryBuilder().where(ToDoBeanDao.Properties.Id.eq(id)).unique();
    }
}
