package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.customer.CustomerView;

/**
 * Created by Administrator on 2017/6/22.
 */
public class CustomerProvider {
    public String getQuerySql(CustomerView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select cid, name, city, address, cname, cphone, naicity, naiaddress, scale, used, left, road, longitude, latitude, remarks from customer");
        if (view.getRows() > 0 && view.getRows() <= 100) {
            buffer.append(" order by cid limit #{rows}");
        }
        if (view.getStart() >= 0) {
            buffer.append(" offset #{start}");
        }
        return buffer.toString();
    }
}
