package com.zhaopin.core.dto.order;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public class OrderView {
    private int start;
    private int rows;
    private int oid;

    public OrderView(int start,int rows){
        this.start = start;
        this.rows = rows;
    }

    public OrderView(int oid){
        this.oid = oid;
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

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
