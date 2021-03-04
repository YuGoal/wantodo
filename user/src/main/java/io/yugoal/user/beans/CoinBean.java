package io.yugoal.user.beans;

/**
 * user caoyu
 * date 2021/3/4
 * time 17:12
 */
public class CoinBean {


    /**
     * coinCount : 2337
     * level : 24
     * nickname :
     * rank : 591
     * userId : 8309
     * username : y**oal
     */

    private Integer coinCount;
    private Integer level;
    private String nickname;
    private String rank;
    private Integer userId;
    private String username;

    public Integer getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(Integer coinCount) {
        this.coinCount = coinCount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
