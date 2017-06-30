package com.zhaopin.core.dto;

import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.DataUserModel;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.model.TrackModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public class OrderAddInfoDto {
    //提示信息
    private String info;
    //所有牧场信息
    private List<CustomerModel> customers;
    //所有货车信息
    private List<TrackModel> tracks;
    //数据员
    private List<DataUserModel> dataUser;
    //订单信息
    private OrderModel orderModel;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<CustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerModel> customers) {
        this.customers = customers;
    }

    public List<TrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackModel> tracks) {
        this.tracks = tracks;
    }

    public List<DataUserModel> getDataUser() {
        return dataUser;
    }

    public void setDataUser(List<DataUserModel> dataUser) {
        this.dataUser = dataUser;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
}
