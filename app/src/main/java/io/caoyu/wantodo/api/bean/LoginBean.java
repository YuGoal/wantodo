package io.caoyu.wantodo.api.bean;

import java.util.List;

/**
 * user caoyu
 * date 2020/11/13
 * time 15:46
 */
public class LoginBean {

    /**
     * admin : false
     * chapterTops : []
     * coinCount : 0
     * collectIds : [15215]
     * email :
     * icon :
     * id : 8309
     * nickname : yugoal
     * password :
     * publicName : yugoal
     * token :
     * type : 0
     * username : yugoal
     */

    private Boolean admin;
    private Integer coinCount;
    private String email;
    private String icon;
    private Integer id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private Integer type;
    private String username;
    private List<?> chapterTops;
    private List<Integer> collectIds;

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(Integer coinCount) {
        this.coinCount = coinCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
