package com.zhaopin.core.model;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public class OrderModel {
    //订单id
    private int oid;
    //牧场
    private CustomerModel customerModel;
    //货车
    private TrackModel trackModel;
    //数据员
    private DataUserModel dataUserModel;
    //发货量（吨）
    private int volume;
    //单价
    private double price;
    //总价
    private int total;
    //创建时间
    private String createtime;
    //备注
    private String remarks;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public TrackModel getTrackModel() {
        return trackModel;
    }

    public void setTrackModel(TrackModel trackModel) {
        this.trackModel = trackModel;
    }

    public DataUserModel getDataUserModel() {
        return dataUserModel;
    }

    public void setDataUserModel(DataUserModel dataUserModel) {
        this.dataUserModel = dataUserModel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
