package io.yugoal.lib_todo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @user caoyu
 * @date 2019/11/14
 */
@Entity
public class ToDoBean {

    public long id;
    @NotNull public String title;
    @NotNull public String content;
    @NotNull public String dateStr;
    public int status;
    public int type;
    public int priority;
    public long completeDate;
    public String completeDateStr;
    @NotNull public long date;
    @Generated(hash = 34553191)
    public ToDoBean(long id, @NotNull String title, @NotNull String content,
            @NotNull String dateStr, int status, int type, int priority,
            long completeDate, String completeDateStr, long date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateStr = dateStr;
        this.status = status;
        this.type = type;
        this.priority = priority;
        this.completeDate = completeDate;
        this.completeDateStr = completeDateStr;
        this.date = date;
    }
    @Generated(hash = 911693116)
    public ToDoBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDateStr() {
        return this.dateStr;
    }
    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public long getCompleteDate() {
        return this.completeDate;
    }
    public void setCompleteDate(long completeDate) {
        this.completeDate = completeDate;
    }
    public String getCompleteDateStr() {
        return this.completeDateStr;
    }
    public void setCompleteDateStr(String completeDateStr) {
        this.completeDateStr = completeDateStr;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }

}
