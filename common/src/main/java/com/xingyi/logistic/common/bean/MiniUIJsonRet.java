package com.xingyi.logistic.common.bean;

/**
 * Created by Jadic on 2018/1/8.
 */
public class MiniUIJsonRet <T> extends JsonRet<T> {

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
