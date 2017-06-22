package com.zhaopin.core.dto.customer;

/**
 * Created by Administrator on 2017/6/22.
 */
public class CustomerView {
    private int start;
    private int rows;

    public CustomerView(int start,int rows){
        this.start = start;
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
