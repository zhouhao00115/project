package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.mapper.provider.DataUserProvider;
import com.zhaopin.core.model.DataUserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public interface DataUserMapper {
    @SelectProvider(method = "getQuerySql", type = DataUserProvider.class)
    List<DataUserModel> query(DataUserView view);

    @Select("select staffid,sname from staff")
    List<DataUserModel> allUser();

    @Select("select count(*) from staff")
    int count();

    @Select("select staffid,sname,sphone,gender,remarks from staff where staffid=#{staffid} order by staffid desc limit 1 offset 0")
    DataUserModel getUserById(int staffid);

    @Select("select staffid from staff order by staffid desc limit 1 offset 0")
    int getLastId();

    @Insert("insert into staff (staffid,sname,sphone,gender,remarks) values (#{staffid},#{sname},#{sphone},#{gender},#{remarks})")
    int addDataUser(DataUserModel model);

    @Update("update staff set sname=#{sname},sphone=#{sphone},gender=#{gender},remarks=#{remarks} where staffid=#{staffid}")
    int updateDataUser(DataUserModel model);
}
