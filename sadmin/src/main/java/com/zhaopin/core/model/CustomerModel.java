package com.zhaopin.core.model;

/**
 * Created by Administrator on 2017/6/18.
 */
public class CustomerModel {
    private String id;
    private String name;
    private String city;
    private String phone;
    private int scale;
    private String address;
    private String userName;
    //交奶地址
    private String sendAddress;
    //月用量-吨/月
    private int used;
    private String save;
    //剩余量
    private int surplus;
    //道路情况
    private String road;
    //运费
    private int freight;
    //价格表运费
    private int priceFreight;
    private String dataUser;
    private String dataUserPhone;
    private String remark;
    //坐标值  116.394985,39.908099
    private String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getPriceFreight() {
        return priceFreight;
    }

    public void setPriceFreight(int priceFreight) {
        this.priceFreight = priceFreight;
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }

    public String getDataUserPhone() {
        return dataUserPhone;
    }

    public void setDataUserPhone(String dataUserPhone) {
        this.dataUserPhone = dataUserPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
