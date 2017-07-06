package com.zhaopin.core.model;

/**
 * Created by zhou.hao on 2017/7/6.
 */
public class ReportTimeModel {
    private String startDay;
    private String endDay;

    public ReportTimeModel(String startDay, String endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
