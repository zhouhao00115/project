package com.zhaopin.core.dto.report;

import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.ReportModel;

import java.util.List;
/**
 * Created by zhou.hao on 2017/7/5.
 */
public class ReportTableDto {
    private String cid;
    private String cname;
    private CustomerModel customerModel;
    private List<ReportModel> models;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<ReportModel> getModels() {
        return models;
    }

    public void setModels(List<ReportModel> models) {
        this.models = models;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
}
