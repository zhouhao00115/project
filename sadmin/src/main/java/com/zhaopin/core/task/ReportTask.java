package com.zhaopin.core.task;

import com.zhaopin.core.dao.OrderDao;
import com.zhaopin.core.dao.ReportDao;
import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Map;

/**
 * 该任务负责每天运行一次，用户统计生成报表数据
 * <p>
 * Created by zhou.hao on 2017/7/4.
 */
public class ReportTask {

    public static void main(String[] args) {
        System.out.println(DateUtil.convert2String(new Date(), DateUtil.formatDate_01) + "--汇总昨天处理的数据任务开始");
        ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        OrderDao orderDao = context.getBean(OrderDao.class);
        ReportDao reportDao = context.getBean(ReportDao.class);
        Map<String, Integer> counts = orderDao.countOrderByCustomerAndDay("123");
        for (String key : counts.keySet()) {
            ReportModel model = new ReportModel();
            int rows = reportDao.addReportData(model);
            if (rows > 0) {
                System.out.println(key + "--" + counts.get(key) + "---success");
            } else {
                System.out.println(key + "--" + counts.get(key) + "---error");
            }
        }
    }
}
