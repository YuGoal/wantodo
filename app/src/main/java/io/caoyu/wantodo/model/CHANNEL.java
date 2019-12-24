package io.caoyu.wantodo.model;

/**
 * @user caoyu
 * @date 2019/11/13
 */
public enum  CHANNEL {

    TODO("待办清单", 0x01),

    COMPLETED("已完成清单", 0x02);


    //所有类型标识
    public static final int TODO_ID = 0x01;
    public static final int COMPLETED_ID = 0x02;

    private final String key;
    private final int value;

    CHANNEL(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}

