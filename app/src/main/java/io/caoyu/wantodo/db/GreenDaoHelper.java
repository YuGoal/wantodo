package io.caoyu.wantodo.db;

import android.database.sqlite.SQLiteDatabase;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.model.DaoMaster;
import io.caoyu.wantodo.model.DaoSession;
import io.caoyu.wantodo.model.LabelBeanDao;
import io.caoyu.wantodo.model.ToDoBeanDao;
import io.caoyu.wantodo.repository.ToDoHelper;


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

    /**
     * 添加清单
     */
    public static void addToDo(ToDoBean toDoBean) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.insert(toDoBean);
    }

    /**
     * 删除清单
     *
     * @param id
     */
    public static void deleteToDo(long id) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.delete(selectToDoBean(id));
    }

    /**
     * 修改清单
     *
     * @param toDoBean
     */
    public static void updataToDo(ToDoBean toDoBean) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        dao.insertOrReplace(toDoBean);
    }

    /**
     * 获取清单列表
     *
     * @param type     标签
     * @param priority 优先级
     * @param orderby  顺序
     * @return
     */
    public static List<ToDoBean> getToDoList(int type, int priority, int orderby) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        List<ToDoBean> list = new ArrayList<>();
        QueryBuilder<ToDoBean> queryBuilder = dao.queryBuilder().where(
                ToDoBeanDao.Properties.Priority.eq(priority),
                ToDoBeanDao.Properties.Type.eq(type)
        ).orderAsc(ToDoBeanDao.Properties.Status);
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

    /**
     * 完成清单
     *
     * @param id
     * @param status
     */
    public static void completedToDo(long id, int status) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        ToDoBean toDoBean = selectToDoBean(id);
        toDoBean.setStatus(status);
        dao.insertOrReplace(toDoBean);
    }

    /**
     * 查询单个清单
     *
     * @param id
     * @return
     */
    public static ToDoBean selectToDoBean(long id) {
        ToDoBeanDao dao = mDaoSession.getToDoBeanDao();
        return dao.queryBuilder().where(ToDoBeanDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 新增标签
     */
    public static void addLabel(LabelBean labelBean) {
        LabelBeanDao dao = mDaoSession.getLabelBeanDao();
        dao.insert(labelBean);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public static void deleteLabel(long id) {
        LabelBeanDao dao = mDaoSession.getLabelBeanDao();
        dao.delete(getLabel(id));
    }


    /**
     * 修改标签
     */
    public static void updataLabel(LabelBean labelBean) {
        LabelBeanDao dao = mDaoSession.getLabelBeanDao();
        dao.insertOrReplace(labelBean);
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    public static List<LabelBean> getLabelList() {
        LabelBeanDao dao = mDaoSession.getLabelBeanDao();
        return dao.queryBuilder().list();
    }

    /**
     * 获取单个标签
     * @param id
     * @return
     */
    public static LabelBean getLabel(long id) {
        LabelBeanDao dao = mDaoSession.getLabelBeanDao();
        return dao.queryBuilder().where(LabelBeanDao.Properties.Id.eq(id)).unique();
    }
}
