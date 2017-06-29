package com.zhaopin.core.dto.Track;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public class TrackView {
    private int start;
    private int rows;
    private String tid;

    public TrackView(int start, int rows) {
        this.start = start;
        this.rows = rows;
    }

    public TrackView(String tid) {
        this.tid = tid;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
