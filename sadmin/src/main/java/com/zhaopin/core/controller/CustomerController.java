package com.zhaopin.core.controller;

import com.zhaopin.core.dao.CustomerDao;
import com.zhaopin.core.dao.TestDao;
import com.zhaopin.core.dto.CustomerDto;
import com.zhaopin.core.dto.MuChangDto;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.MuChang;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
public class CustomerController {
    @Autowired
    private TestDao dao;

    @RequestMapping(value = "customer.do", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.printf("customer index");
        dao.test();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        List<CustomerModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CustomerModel model = new CustomerModel();
            model.setId("CHS00" + i);
            model.setCity("衡水市");
            model.setName("张三牧场");
            model.setPhone("13500000001");
            model.setDataUser("李四");
            model.setDataUserPhone("1360001111");
            Random random = new Random();
            model.setScale(random.nextInt(4));
            double ll = 116.220784 - Math.random();
            double ee = 38.271844 - Math.random();
            model.setPosition(String.format("%s,%s", ll, ee));
            list.add(model);
        }
        CustomerDto dto = new CustomerDto();
        dto.setCount(list.size());
        dto.setList(list);
        dto.setPage(1);
        mv.addObject("number", 1);
        mv.addObject("dto", dto);
        return mv;
    }

    @RequestMapping(value = "customerinfo.do", method = RequestMethod.GET)
    public ModelAndView customerinfo(@RequestParam(value = "number", defaultValue = "") String number) {
        System.out.printf("customer index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        mv.addObject("number", 2);
        if (StringUtil.idNullOrEmpty(number)) {
            return mv;
        }
        CustomerModel model = new CustomerModel();
        model.setId(number);
        model.setCity("衡水市");
        model.setName("张三牧场");
        model.setPhone("13500000001");
        model.setDataUser("李四");
        model.setDataUserPhone("1360001111");
        Random random = new Random();
        model.setScale(random.nextInt(4));
        model.setAddress("衡水市XX市XX乡XX村");
        model.setUserName("张三");
        model.setSendAddress("衡水市XX市XX乡XX村");
        model.setUsed(10);
        model.setSave("");
        model.setSurplus(130);
        model.setRoad("全程高速");
        model.setFreight(30);
        model.setPriceFreight(0);
        model.setRemark("备注");
        mv.addObject("dto", model);
        return mv;
    }

    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    public ModelAndView test() {
        System.out.printf("customer index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("maptest");
        return mv;
    }

}
