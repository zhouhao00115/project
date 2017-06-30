package com.zhaopin.core.model;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public class CustomerResultModel {
    private String cid;
    private String name;
    private String city;
    private String address;
    private String cname;
    private String cphone;
    private String naicity;
    private String naiaddress;
    private String scale;
    private int used;
    private int left;
    private String road;
    private int price;
    private double longitude;
    private double latitude;
    private String remarks;
    private DataUserModel dataUserModel;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getNaicity() {
        return naicity;
    }

    public void setNaicity(String naicity) {
        this.naicity = naicity;
    }

    public String getNaiaddress() {
        return naiaddress;
    }

    public void setNaiaddress(String naiaddress) {
        this.naiaddress = naiaddress;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public DataUserModel getDataUserModel() {
        return dataUserModel;
    }

    public void setDataUserModel(DataUserModel dataUserModel) {
        this.dataUserModel = dataUserModel;
    }
}
