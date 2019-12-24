package io.caoyu.wantodo.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @user caoyu
 * @date 2019/11/22
 */
@Entity
public class LabelBean {
    @Id
    public Long id;

    private String label;

    @Generated(hash = 293510124)
    public LabelBean(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    @Generated(hash = 1285554626)
    public LabelBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}
