package io.caoyu.wantodo.api.bean;

/**
 * user caoyu
 * date 2020/11/10
 * time 14:52
 */
public class CoinBean {

    /**
     * coinCount : 451
     * rank : 7
     * userId : 2
     * username : x**oyang
     */

    private Integer coinCount;
    private Integer rank;
    private Integer userId;
    private String username;

    public Integer getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(Integer coinCount) {
        this.coinCount = coinCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
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
