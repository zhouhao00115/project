package com.zhaopin.core.dto;


import com.zhaopin.core.model.TrackModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public class TrackDto {
    private int count;
    private int page;
    private int start;
    private int rows;
    private int end;
    private List<TrackModel> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<TrackModel> getList() {
        return list;
    }

    public void setList(List<TrackModel> list) {
        this.list = list;
    }
}
