package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.mapper.provider.OrderProvider;
import com.zhaopin.core.model.OrderModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public interface OrderMapper {
    @SelectProvider(method = "getQuerySql", type = OrderProvider.class)
    @Results({
            @Result(id=true,column="oid",property="oid"),
            @Result(column="cid",property="customerModel",many = @Many(select = "com.zhaopin.core.mapper.CustomerMapper.getCustomerById")),
            @Result(column="tid",property="trackModel",many = @Many(select = "com.zhaopin.core.mapper.TrackMapper.getTrackById")),
            @Result(column="staffid",property="dataUserModel",many = @Many(select = "com.zhaopin.core.mapper.DataUserMapper.getUserById"))
    })
    List<OrderModel> query(OrderView view);

    @Select("select count(*) from orders")
    int count();

    @Select("select oid from orders order by oid desc limit 1 offset 0")
    int getLastId();

    @Select("select oid,cid,tid,staffid,volume,price,total,createtime,remarks from orders where oid=#{oid}")
    @Results({
            @Result(id=true,column="oid",property="oid"),
            @Result(column="cid",property="customerModel",many = @Many(select = "com.zhaopin.core.mapper.CustomerMapper.getCustomerById")),
            @Result(column="tid",property="trackModel",many = @Many(select = "com.zhaopin.core.mapper.TrackMapper.getTrackById")),
            @Result(column="staffid",property="dataUserModel",many = @Many(select = "com.zhaopin.core.mapper.DataUserMapper.getUserById"))
    })
    OrderModel getModelById(int oid);

    @Insert("insert into orders (oid,cid,tid,staffid,volume,price,total,createtime,remarks) values (#{oid},#{customerModel.cid},#{trackModel.tid},#{dataUserModel.staffid},#{volume},#{price},#{total},#{createtime},#{remarks})")
    int addOrders(OrderModel model);

    @Update("update orders set cid=#{customerModel.cid},tid=#{trackModel.tid},staffid=#{dataUserModel.staffid},volume=#{volume},price=#{price},total=#{total},remarks=#{remarks} where oid=#{oid}")
    int updateOrders(OrderModel model);
}
