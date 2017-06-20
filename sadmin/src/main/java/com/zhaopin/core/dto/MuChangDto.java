package com.zhaopin.core.dto;

import com.zhaopin.core.model.MuChang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class MuChangDto {
    private int count;
    private List<MuChang> list;
    private List<String> stateList;
    private int state;
    private int page;

    public MuChangDto(){
        this.stateList = new ArrayList<>();
        stateList.add("充足");
        stateList.add("正常");
        stateList.add("缺货");
        stateList.add("急缺");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MuChang> getList() {
        return list;
    }

    public void setList(List<MuChang> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
