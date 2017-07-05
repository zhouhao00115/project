package com.zhaopin.core.dto.report;

import java.util.List;
import java.util.Map;

/**
 * Created by zhou.hao on 2017/7/5.
 */
public class ReportTableDto {
    private List<String> dataKeys;
    private List<String> customerKeys;
    private Map<String,Map<String,String>> values;

    public List<String> getDataKeys() {
        return dataKeys;
    }

    public void setDataKeys(List<String> dataKeys) {
        this.dataKeys = dataKeys;
    }

    public List<String> getCustomerKeys() {
        return customerKeys;
    }

    public void setCustomerKeys(List<String> customerKeys) {
        this.customerKeys = customerKeys;
    }

    public Map<String, Map<String, String>> getValues() {
        return values;
    }

    public void setValues(Map<String, Map<String, String>> values) {
        this.values = values;
    }
}
