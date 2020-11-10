package io.caoyu.wantodo.api.bean;

import java.util.List;

/**
 * user caoyu
 * date 2020/11/10
 * time 14:48
 */
public class TreeBean {

    /**
     * children : [{"children":[],"courseId":13,"id":458,"name":"mmap","order":215000,"parentChapterId":457,"userControlSetTop":false,"visible":1}]
     * courseId : 13
     * id : 457
     * name : linux
     * order : 215
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    private Integer courseId;
    private Integer id;
    private String name;
    private Integer order;
    private Integer parentChapterId;
    private Boolean userControlSetTop;
    private Integer visible;
    private List<ChildrenBean> children;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(Integer parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public Boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(Boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * children : []
         * courseId : 13
         * id : 458
         * name : mmap
         * order : 215000
         * parentChapterId : 457
         * userControlSetTop : false
         * visible : 1
         */

        private Integer courseId;
        private Integer id;
        private String name;
        private Integer order;
        private Integer parentChapterId;
        private Boolean userControlSetTop;
        private Integer visible;
        private List<?> children;

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public Integer getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(Integer parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public Boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(Boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public Integer getVisible() {
            return visible;
        }

        public void setVisible(Integer visible) {
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
