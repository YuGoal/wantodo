package io.caoyu.wantodo.api.bean;

/**
 * user caoyu
 * date 2020/11/10
 * time 14:50
 */
public class HotkeyBean {

    /**
     * id : 8
     * link :
     * name : 逆向 加固
     * order : 8
     * visible : 1
     */

    private Integer id;
    private String link;
    private String name;
    private Integer order;
    private Integer visible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
