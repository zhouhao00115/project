package com.zhaopin.core.task;

import com.zhaopin.core.util.DateUtil;

import java.util.Date;

/**
 * 该任务负责每天运行一次，用户统计生成报表数据
 *
 * Created by zhou.hao on 2017/7/4.
 */
public class ReportTask {
    public static void main(String[] args) {
        System.out.println(DateUtil.convert2String(new Date(),DateUtil.formatDate_01)+"--定时任务开始");
    }
}
