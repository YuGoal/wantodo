package io.caoyu.wantodo.api.bean;

import java.util.List;

public class ArticleBean {


    /**
     * curPage : 2
     * datas : [{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":16025,"link":"https://mp.weixin.qq.com/s/MPZD9LbSbJYx1BLAolIbIg","niceDate":"2020-11-07 00:00","niceShareDate":"13小时前","origin":"","prefix":"","projectLink":"","publishTime":1604678400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604940244000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"WorkManager 流程分析和源码解析 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":99,"chapterName":"具体案例","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15992,"link":"https://juejin.im/post/6891573246114660365","niceDate":"2020-11-06 00:15","niceShareDate":"2020-11-06 00:11","origin":"","prefix":"","projectLink":"","publishTime":1604592925000,"realSuperChapterId":37,"selfVisible":0,"shareDate":1604592672000,"shareUser":"鸿洋","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"为自己的开源控件ShadowLayout3.0不止于阴影，打打气","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":320,"chapterName":"内存管理","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15993,"link":"https://juejin.im/post/6891589544161116168#heading-14","niceDate":"2020-11-06 00:15","niceShareDate":"2020-11-06 00:11","origin":"","prefix":"","projectLink":"","publishTime":1604592917000,"realSuperChapterId":244,"selfVisible":0,"shareDate":1604592691000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"《Android 工程师进阶》笔记3：GC 回收机制与分代回收策略","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":99,"chapterName":"具体案例","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15994,"link":"https://juejin.im/post/6891284263174406157","niceDate":"2020-11-06 00:15","niceShareDate":"2020-11-06 00:11","origin":"","prefix":"","projectLink":"","publishTime":1604592905000,"realSuperChapterId":37,"selfVisible":0,"shareDate":1604592706000,"shareUser":"鸿洋","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"TextView 实现跟随标签","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":228,"chapterName":"辅助 or 工具类","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15995,"link":"https://www.jianshu.com/p/e2eb509b04e7","niceDate":"2020-11-06 00:14","niceShareDate":"2020-11-06 00:12","origin":"","prefix":"","projectLink":"","publishTime":1604592895000,"realSuperChapterId":156,"selfVisible":0,"shareDate":1604592751000,"shareUser":"鸿洋","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"排障困难？给你的应用嵌入一个Logcat吧!","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15996,"link":"https://www.jianshu.com/p/f9e42a4d5a28","niceDate":"2020-11-06 00:14","niceShareDate":"2020-11-06 00:12","origin":"","prefix":"","projectLink":"","publishTime":1604592885000,"realSuperChapterId":53,"selfVisible":0,"shareDate":1604592774000,"shareUser":"鸿洋","superChapterId":76,"superChapterName":"热门专题","tags":[],"title":"Android 性能测试 - 流畅度","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15999,"link":"https://mp.weixin.qq.com/s/U_c1l25kSUwnVLcIWYVBcQ","niceDate":"2020-11-06 00:00","niceShareDate":"2020-11-06 23:56","origin":"","prefix":"","projectLink":"","publishTime":1604592000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604678179000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"实现边到边的体验 | 让您的软键盘动起来 (一)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":16000,"link":"https://mp.weixin.qq.com/s/ThrdbShSJOj0uRU3XpBEpg","niceDate":"2020-11-06 00:00","niceShareDate":"2020-11-06 23:56","origin":"","prefix":"","projectLink":"","publishTime":1604592000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604678192000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"深入探索 Android Gradle 插件的缓存配置","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":16001,"link":"https://mp.weixin.qq.com/s/p7tegwkkiS-LJax9n-Mu0w","niceDate":"2020-11-06 00:00","niceShareDate":"2020-11-06 23:56","origin":"","prefix":"","projectLink":"","publishTime":1604592000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604678207000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"看 Google 是如何设计的 View 机制？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":16002,"link":"https://mp.weixin.qq.com/s/VJDBg2X5Bbus8hcbcRxe4A","niceDate":"2020-11-06 00:00","niceShareDate":"2020-11-06 23:57","origin":"","prefix":"","projectLink":"","publishTime":1604592000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604678220000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"ConstraintLayout和MotionLayout使用进阶","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15982,"link":"https://www.jianshu.com/p/434b3075b5dd","niceDate":"2020-11-05 00:25","niceShareDate":"2020-11-05 00:23","origin":"","prefix":"","projectLink":"","publishTime":1604507136000,"realSuperChapterId":156,"selfVisible":0,"shareDate":1604507016000,"shareUser":"鸿洋","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"一行代码帮你检测Android模拟器","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15983,"link":"https://juejin.im/post/6888990959233662990","niceDate":"2020-11-05 00:25","niceShareDate":"2020-11-05 00:24","origin":"","prefix":"","projectLink":"","publishTime":1604507125000,"realSuperChapterId":156,"selfVisible":0,"shareDate":1604507088000,"shareUser":"鸿洋","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"切记，不要在你的App启动界面设置SingleTask/SingleInstance","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15987,"link":"https://mp.weixin.qq.com/s/g9bWSg9b_PH4NrdrpAckvw","niceDate":"2020-11-05 00:00","niceShareDate":"2020-11-05 21:42","origin":"","prefix":"","projectLink":"","publishTime":1604505600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604583758000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"深入理解 Dart 空安全","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15988,"link":"https://mp.weixin.qq.com/s/O1NVCZtlfWN2cY-IhseMGg","niceDate":"2020-11-05 00:00","niceShareDate":"2020-11-05 21:42","origin":"","prefix":"","projectLink":"","publishTime":1604505600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604583778000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"双 11 快到了，不给你的 APP 加上自动换图标的功能吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15989,"link":"https://mp.weixin.qq.com/s/UuU4o3_ZUoVhdadyK_DEeQ","niceDate":"2020-11-05 00:00","niceShareDate":"2020-11-05 21:43","origin":"","prefix":"","projectLink":"","publishTime":1604505600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604583793000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"又一个知识点&ldquo;理解错了&rdquo;，源码变啦！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15990,"link":"https://mp.weixin.qq.com/s/h-Sji-2z2MEFKuw3WXS9ng","niceDate":"2020-11-05 00:00","niceShareDate":"2020-11-05 21:43","origin":"","prefix":"","projectLink":"","publishTime":1604505600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604583811000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"5张图搞懂Android系统启动的核心流程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15978,"link":"https://blog.csdn.net/u014068277/article/details/109484524","niceDate":"2020-11-04 09:53","niceShareDate":"2020-11-04 09:53","origin":"","prefix":"","projectLink":"","publishTime":1604454818000,"realSuperChapterId":493,"selfVisible":0,"shareDate":1604454818000,"shareUser":"xujiafeng","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"寻找二叉树两个节点的公共父节点","type":0,"userId":225,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":182,"chapterName":"JNI编程","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15972,"link":"https://blog.csdn.net/luo_boke/article/details/109455910","niceDate":"2020-11-04 00:29","niceShareDate":"2020-11-03 18:28","origin":"","prefix":"","projectLink":"","publishTime":1604420953000,"realSuperChapterId":128,"selfVisible":0,"shareDate":1604399298000,"shareUser":"深红骑士","superChapterId":149,"superChapterName":"JNI","tags":[],"title":"Android JNI的深度进阶学习","type":0,"userId":29303,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"Android群英传","canEdit":false,"chapterId":413,"chapterName":"Android群英传","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15979,"link":"https://mp.weixin.qq.com/s/mKzRrKnoRhLwguLO02QtmA","niceDate":"2020-11-04 00:00","niceShareDate":"2020-11-04 20:30","origin":"","prefix":"","projectLink":"","publishTime":1604419200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604493040000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/413/1"}],"title":"Android | 带你探究 LayoutInflater 布局解析原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":15980,"link":"https://mp.weixin.qq.com/s/lBS1PpWeIXLFjkGfOZilyw","niceDate":"2020-11-04 00:00","niceShareDate":"2020-11-04 20:30","origin":"","prefix":"","projectLink":"","publishTime":1604419200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1604493056000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"对标 RxJava，你好 Flow，协程实战篇","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 20
     * over : false
     * pageCount : 475
     * size : 20
     * total : 9491
     */

    private Integer curPage;
    private Integer offset;
    private Boolean over;
    private Integer pageCount;
    private Integer size;
    private Integer total;
    private List<DatasBean> datas;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean isOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * apkLink :
         * audit : 1
         * author : 谷歌开发者
         * canEdit : false
         * chapterId : 415
         * chapterName : 谷歌开发者
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : false
         * id : 16025
         * link : https://mp.weixin.qq.com/s/MPZD9LbSbJYx1BLAolIbIg
         * niceDate : 2020-11-07 00:00
         * niceShareDate : 13小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1604678400000
         * realSuperChapterId : 407
         * selfVisible : 0
         * shareDate : 1604940244000
         * shareUser :
         * superChapterId : 408
         * superChapterName : 公众号
         * tags : [{"name":"公众号","url":"/wxarticle/list/415/1"}]
         * title : WorkManager 流程分析和源码解析 | 开发者说&middot;DTalk
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private Integer audit;
        private String author;
        private Boolean canEdit;
        private Integer chapterId;
        private String chapterName;
        private Boolean collect;
        private Integer courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private Boolean fresh;
        private Integer id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private Long publishTime;
        private Integer realSuperChapterId;
        private Integer selfVisible;
        private Long shareDate;
        private String shareUser;
        private Integer superChapterId;
        private String superChapterName;
        private String title;
        private Integer type;
        private Integer userId;
        private Integer visible;
        private Integer zan;
        private List<TagsBean> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public Integer getAudit() {
            return audit;
        }

        public void setAudit(Integer audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(Boolean canEdit) {
            this.canEdit = canEdit;
        }

        public Integer getChapterId() {
            return chapterId;
        }

        public void setChapterId(Integer chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public Boolean isCollect() {
            return collect;
        }

        public void setCollect(Boolean collect) {
            this.collect = collect;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public Boolean isFresh() {
            return fresh;
        }

        public void setFresh(Boolean fresh) {
            this.fresh = fresh;
        }

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

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public Long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Long publishTime) {
            this.publishTime = publishTime;
        }

        public Integer getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setRealSuperChapterId(Integer realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public Integer getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(Integer selfVisible) {
            this.selfVisible = selfVisible;
        }

        public Long getShareDate() {
            return shareDate;
        }

        public void setShareDate(Long shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public Integer getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(Integer superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getVisible() {
            return visible;
        }

        public void setVisible(Integer visible) {
            this.visible = visible;
        }

        public Integer getZan() {
            return zan;
        }

        public void setZan(Integer zan) {
            this.zan = zan;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * name : 公众号
             * url : /wxarticle/list/415/1
             */

            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
