package com.zhaopin.core.dto;

import com.zhaopin.core.model.DataUserModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public class DataUserDto {
    private int count;
    private int page;
    private List<DataUserModel> list;

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

    public List<DataUserModel> getList() {
        return list;
    }

    public void setList(List<DataUserModel> list) {
        this.list = list;
    }
}
