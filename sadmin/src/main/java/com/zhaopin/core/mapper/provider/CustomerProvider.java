package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.customer.CustomerView;

/**
 * Created by Administrator on 2017/6/22.
 */
public class CustomerProvider {
    public String getQuerySql(CustomerView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select * " +
                "from customer as t1,(select staffid,sname,sphone from staff) as t2 where t1.staffid=t2.staffid");
        if (view.getRows() < 1 || view.getRows() > 100) {
            view.setRows(10);
        }
        if (view.getStart() < 0) {
            view.setStart(0);
        }
        buffer.append(" order by cid desc limit #{rows} offset #{start}");
        return buffer.toString();
    }
}
