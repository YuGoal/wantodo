package io.caoyu.wantodo.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * @user caoyu
 * @date 2019/11/14
 */
@Entity(tableName = "todo_table")
public class ToDoBean implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int todoId = 0;
    public String title;
    public String content;
    public String dateStr;
    public int status;
    public int type;
    public int priority;
    public long completeDate;
    public String completeDateStr;
    public String date;


    protected ToDoBean(Parcel in) {
        todoId = in.readInt();
        title = in.readString();
        content = in.readString();
        dateStr = in.readString();
        status = in.readInt();
        type = in.readInt();
        priority = in.readInt();
        completeDate = in.readLong();
        completeDateStr = in.readString();
        date = in.readString();
    }

    public static final Creator<ToDoBean> CREATOR = new Creator<ToDoBean>() {
        @Override
        public ToDoBean createFromParcel(Parcel in) {
            return new ToDoBean(in);
        }

        @Override
        public ToDoBean[] newArray(int size) {
            return new ToDoBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(todoId);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(dateStr);
        dest.writeInt(status);
        dest.writeInt(type);
        dest.writeInt(priority);
        dest.writeLong(completeDate);
        dest.writeString(completeDateStr);
        dest.writeString(date);
    }
}
