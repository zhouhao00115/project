package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.dto.datauser.DataUserView;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public class TrackProvider {
    public String getQuerySql(TrackView view) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select tid,license,tname,tphone,ttype,capacity,citys,remarks from truck");
        if (view.getRows() < 1 || view.getRows() > 100) {
            view.setRows(10);
        }
        if (view.getStart() < 0) {
            view.setStart(0);
        }
        buffer.append(" order by tid desc limit #{rows} offset #{start}");
        return buffer.toString();
    }
}
