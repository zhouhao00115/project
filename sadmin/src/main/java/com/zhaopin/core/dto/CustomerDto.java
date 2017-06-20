package com.zhaopin.core.dto;

import com.zhaopin.core.model.CustomerModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class CustomerDto {
    private int count;
    private int page;
    private List<CustomerModel> list;

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

    public List<CustomerModel> getList() {
        return list;
    }

    public void setList(List<CustomerModel> list) {
        this.list = list;
    }
}
