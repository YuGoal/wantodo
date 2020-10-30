package io.caoyu.wantodo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.caoyu.wantodo.application.TodoApplication;
import io.caoyu.wantodo.model.ToDoBean;

/**
 * user caoyu
 * date 2020/10/30
 * time 11:48
 */
@Database(entities = {ToDoBean.class} ,version = 1,exportSchema = false)
public abstract class TodoDataBase extends RoomDatabase {
    private static final String DB_NAME = "TodoDataBase.db";
    private static volatile TodoDataBase singleton = null;

    private TodoDataBase() {}

    public static TodoDataBase getInstance() {
        if (singleton == null) {
            synchronized (TodoDataBase.class) {
                if (singleton == null) {
                    singleton = create(TodoApplication.getInstance().getApplicationContext());
                }
            }
        }
        return singleton;
    }
    private static TodoDataBase create(final Context context) {
        return Room.databaseBuilder(context, TodoDataBase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public abstract TodoDao getTodoDao();
}
