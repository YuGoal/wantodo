package io.caoyu.wantodo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.caoyu.wantodo.model.ToDoBean;

/**
 * user caoyu
 * date 2020/10/30
 * time 11:59
 */
@Dao
public interface TodoDao {

    /**
     * 添加清单
     */
    @Insert
    void addToDo(ToDoBean... toDoBean);

    /**
     * 删除清单
     */
    @Delete
     void deleteToDo(ToDoBean... toDoBean);

    /**
     * 修改清单
     * @param toDoBean
     */
    @Update
    void updateToDo(ToDoBean toDoBean);

    /**
     * 获取清单列表
     *
     * @param type     标签
     * @param priority 优先级
     * @param orderby  顺序
     * @return
     */
    @Query("SELECT * FROM todo_table " +
            "WHERE (priority=:priority AND type=:type) " +
            "ORDER BY status=:orderby DESC LIMIT 20 OFFSET :page")
    List<ToDoBean> getToDoList(int type, int priority, int orderby,int page);


    /**
     * 查询单个清单
     *
     * @param id
     * @return
     */
    @Query("SELECT * FROM todo_table " +
            "WHERE (todoId=:id) ")
    ToDoBean selectToDoBean(long id);
}
