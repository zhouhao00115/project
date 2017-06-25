package com.zhaopin.core.dto.datauser;

/**
 * Created by Administrator on 2017/6/25.
 */
public class DataUserView {
    private int start;
    private int rows;
    private int staffid;

    public DataUserView (int start, int rows){
        this.start = start;
        this.rows = rows;
    }

    public DataUserView (int staffid){
        this.staffid = staffid;
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

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }
}
