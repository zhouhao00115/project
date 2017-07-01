package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.order.OrderView;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public class OrderProvider {
    public String getQuerySql(OrderView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select oid,cid,tid,staffid,volume,price,total,createtime,remarks from orders where oid>0");
        if (view.getRows() < 1 || view.getRows() > 100) {
            view.setRows(10);
        }
        if (view.getStart() < 0) {
            view.setStart(0);
        }
        buffer.append(" order by oid desc limit #{rows} offset #{start}");
        return buffer.toString();
    }
}
