package com.zhaopin.core.dto.admin;

/**
 * Created by zhou.hao on 2017/6/27.
 */
public class AdminView {
    private int start;
    private int rows;
    private int id;

    public AdminView(int start, int rows) {
        this.start = start;
        this.rows = rows;
    }

    public AdminView(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
