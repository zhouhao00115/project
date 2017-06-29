package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.mapper.provider.TrackProvider;
import com.zhaopin.core.model.TrackModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public interface TrackMapper {

    @SelectProvider(method = "getQuerySql", type = TrackProvider.class)
    List<TrackModel> query(TrackView view);

    @Select("select tid,license,tname,tphone,ttype,capacity,citys,remarks from truck")
    List<TrackModel> allTrack();

    @Select("select count(*) from truck")
    int count();

    @Select("select tid,license,tname,tphone,ttype,capacity,citys,remarks from truck where tid=#{tid} order by tid desc limit 1 offset 0")
    TrackModel getTrackById(int tid);

    @Select("select tid from truck order by truck desc limit 1 offset 0")
    int getLastId();

    @Insert("insert into truck (tid,license,tname,tphone,ttype,capacity,citys,remarks) values (#{tid},#{license},#{tname},#{tphone},#{ttype},#{capacity},#{citys},#{remarks})")
    int addTrack(TrackModel model);

    @Update("update truck set tid=#{tid},license=#{license},tname=#{tname},tphone=#{tphone},ttype=#{ttype},capacity=#{capacity},citys=#{citys},remarks=#{remarks} where tid=#{tid}")
    int updateTrack(TrackModel model);
}
