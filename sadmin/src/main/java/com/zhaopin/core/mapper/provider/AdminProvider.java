package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.dto.customer.CustomerView;

/**
 * Created by zhou.hao on 2017/6/27.
 */
public class AdminProvider {
    public String getQuerySql(AdminView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select id,username,power from user");
        if (view.getRows() < 1 || view.getRows() > 100) {
            view.setRows(10);
        }
        if (view.getStart() < 0) {
            view.setStart(0);
        }
        buffer.append(" order by id desc limit #{rows} offset #{start}");
        return buffer.toString();
    }
}
