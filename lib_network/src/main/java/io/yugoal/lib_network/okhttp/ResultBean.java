package io.yugoal.lib_network.okhttp;

/**
 * @ProjectName: ITCHighWayMPay
 * @Package: com.highway.itcmpay.retrofit
 * @ClassName: ResultBean
 * @Description:
 * @Author: LiuTao
 * @CreateDate: 2020/9/18 18:03
 * @UpdateUser: LiuTao
 */
public class ResultBean<T> {

    /**
     * code : 604
     * msg : p9zy8c
     */

    private int errorCode;
    private String errorMsg;
    private T data;

    public ResultBean() {
    }

    public ResultBean(int code, String msg, T t) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.data = t;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
