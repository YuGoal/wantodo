package io.caoyu.wantodo.event;

/**
 * @user caoyu
 * @date 2019/11/15
 */
public class ToDoStatusEvent {

    private boolean status;

    public ToDoStatusEvent(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
