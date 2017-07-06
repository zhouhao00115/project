package com.zhaopin.core.mapper;

import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.model.ReportTimeModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhou.hao on 2017/7/5.
 */
public interface ReportMapper {
    @Insert("insert into report (rid,cid,createdate,total,remark) values (#{rid},#{cid},date('now','-1 day'),#{total},#{remark})")
    int addReportData(ReportModel reportModel);

    @Select("select rid from report order by rid desc limit 0,1")
    int getLastId();

    @Select("select count(*) from report")
    int count();

    @Select("select rid,cid,createdate,total,remark from report where createdate>#{startDay} AND createdate<=#{endDay}")
    List<ReportModel> query(ReportTimeModel model);
}
