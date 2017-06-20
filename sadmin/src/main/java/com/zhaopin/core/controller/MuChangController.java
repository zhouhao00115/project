package com.zhaopin.core.controller;

import com.zhaopin.core.dto.MuChangDto;
import com.zhaopin.core.model.MuChang;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/17.
 */
@Controller
public class MuChangController {

    @RequestMapping(value = "muchang.do", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.printf("muchangcontroller index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("muchang");
        List<MuChang> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MuChang mc = new MuChang();
            mc.setId("HS00" + i);
            mc.setName("编号" + i);
            mc.setCity("深州市");
            Random random = new Random();
            mc.setUsed(random.nextInt(100));
            mc.setLeft(500 - mc.getUsed());
            mc.setFreight(random.nextInt(30));
            mc.setRemark("备注" + i);
            list.add(mc);
        }
        for (MuChang mc : list) {
            if (mc.getLeft() < 450) {
                mc.setState(3);
            }
            if (450 <= mc.getLeft() && mc.getLeft() < 480) {
                mc.setState(2);
            }
            if (480 <= mc.getLeft()) {
                mc.setState(1);
            }
        }
        MuChangDto dto = new MuChangDto();
        dto.setCount(list.size());
        dto.setList(list);
        dto.setPage(1);
        dto.setState(0);
        mv.addObject("dto", dto);
        return mv;
    }
}
