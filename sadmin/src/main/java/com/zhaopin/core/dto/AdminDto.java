package com.zhaopin.core.dto;

import com.zhaopin.core.dto.admin.AdminCountDto;
import com.zhaopin.core.model.AdminModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/27.
 */
public class AdminDto {
    private int count;
    private AdminCountDto adminCountDto;
    private int page;
    private int start;
    private int rows;
    private int end;
    private List<AdminModel> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AdminCountDto getAdminCountDto() {
        return adminCountDto;
    }

    public void setAdminCountDto(AdminCountDto adminCountDto) {
        this.adminCountDto = adminCountDto;
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

    public List<AdminModel> getList() {
        return list;
    }

    public void setList(List<AdminModel> list) {
        this.list = list;
    }
}
