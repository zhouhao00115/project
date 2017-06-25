package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.datauser.DataUserView;

/**
 * Created by Administrator on 2017/6/25.
 */
public class DataUserProvider {
    public String getQuerySql(DataUserView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select staffid,sname,sphone,gender,remarks from staff");
        if (view.getRows() < 1 || view.getRows() > 100) {
            view.setRows(10);
        }
        if (view.getStart() < 0) {
            view.setStart(0);
        }
        buffer.append(" order by staffid desc limit #{rows} offset #{start}");
        return buffer.toString();
    }
}
