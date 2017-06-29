package com.zhaopin.core.controller;

import com.zhaopin.core.dao.TrackDao;
import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.dto.TrackDto;
import com.zhaopin.core.model.TrackModel;
import com.zhaopin.core.service.TrackService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
public class TrackController {

    @Autowired
    private TrackService trackService;

    @RequestMapping(value = "track.do", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "start", defaultValue = "0") String numberstart,
                              @RequestParam(value = "rows", defaultValue = "10") String numberrows,
                              @CookieValue(value = "cookierows", defaultValue = "10") String cookierows) {
        int start;
        int rows;
        try {
            start = Integer.parseInt(numberstart);
            rows = Integer.parseInt(numberrows);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("输入的行数不为整数，报错");
            start = 0;
            rows = 10;
        }
        if (request.getParameterMap().containsKey("rows")) {
            Cookie cookie = new Cookie("cookierows", String.valueOf(rows));
            cookie.setPath("/track.do");
            cookie.setMaxAge(30 * 60 * 60 * 15);// 设置为半个月
            response.addCookie(cookie);
        } else {
            try {
                rows = Integer.parseInt(cookierows);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("track");
        List<TrackModel> models = trackService.query(new TrackView(start, rows));
        TrackDto trackDto = new TrackDto();
        trackDto.setList(models);
        trackDto.setPage(start / rows + 1);
        trackDto.setStart(start);
        trackDto.setCount(trackService.count());
        trackDto.setRows(rows);
        if (trackDto.getCount() > rows) {
            if (trackDto.getCount() % rows == 0) {
                trackDto.setEnd((trackDto.getCount() / rows) * rows - rows);
            } else {
                trackDto.setEnd((trackDto.getCount() / rows) * rows);
            }
        }
        mv.addObject("dto", trackDto);
        mv.addObject("view", 1);
        return mv;
    }

    @RequestMapping(value = "trackinfo.do", method = RequestMethod.GET)
    public ModelAndView trackinfo(@RequestParam(value = "tid", defaultValue = "") String tid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("track");
        mv.addObject("view", 2);
        if (StringUtil.isNullOrEmpty(tid)) {
            mv.addObject("dto", new TrackModel());
        } else {
            //标记返回的页面为列表页
            mv.addObject("dto", trackService.getTrackById(tid));
        }
        return mv;
    }

    @RequestMapping(value = "addtrack.do", method = RequestMethod.GET)
    public ModelAndView addtrack(@RequestParam(value = "tid", defaultValue = "") String tid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("track");
        mv.addObject("view", 3);
        mv.addObject("info", "");
        if (StringUtil.isNullOrEmpty(tid)) {
            mv.addObject("dto", new TrackModel());
        } else {
            //标记返回的页面为列表页
            mv.addObject("dto", trackService.getTrackById(tid));
        }
        return mv;
    }

    @RequestMapping(value = "addtrackaction.do", method = RequestMethod.POST)
    public ModelAndView addAction(@RequestParam(value = "tid", defaultValue = "") String tid,
                                  @RequestParam(value = "license", defaultValue = "") String license,
                                  @RequestParam(value = "tname", defaultValue = "") String tname,
                                  @RequestParam(value = "tphone", defaultValue = "") String tphone,
                                  @RequestParam(value = "ttype", defaultValue = "") String ttype,
                                  @RequestParam(value = "capacity", defaultValue = "0") String capacity,
                                  @RequestParam(value = "citys", defaultValue = "") String citys,
                                  @RequestParam(value = "remarks", defaultValue = "") String remarks) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("track");
        mv.addObject("view", 3);
        if (!"1".equals(session.getAttribute("power"))) {
            mv.addObject("info", "修改权限不够");
            mv.addObject("dto", new TrackModel());
            return mv;
        }
        if (StringUtil.isNullOrEmpty(tid)||"0".equals(tid)) {
            //表示添加
            if (StringUtil.isNullOrEmpty(license)) {
                mv.addObject("info", "车牌号不能为空");
                mv.addObject("dto", new TrackModel());
                return mv;
            }
            TrackModel model = new TrackModel();
            model.setLicense(license);
            model.setTname(tname);
            model.setTphone(tphone);
            model.setTtype(ttype);
            int number;
            try {
                number = Integer.parseInt(capacity);
            } catch (Exception e) {
                e.printStackTrace();
                number = 0;
            }
            model.setCapacity(number);
            model.setCitys(citys);
            model.setRemarks(remarks);
            TrackModel returnModel = trackService.addTrack(model);
            if (returnModel.getTid() > 0) {
                mv.addObject("info", "添加成功");
            } else {
                mv.addObject("info", "添加失败");
            }
            mv.addObject("dto", returnModel);
            return mv;
        }
        TrackModel model = new TrackModel();
        int id;
        try {
            id = Integer.parseInt(tid);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("info", "id识别失败");
            model.setTid(0);
            return mv;
        }
        model.setTid(id);
        model.setLicense(license);
        model.setTname(tname);
        model.setTphone(tphone);
        model.setTtype(ttype);
        int number;
        try {
            number = Integer.parseInt(capacity);
        } catch (Exception e) {
            e.printStackTrace();
            number = 0;
        }
        model.setCapacity(number);
        model.setCitys(citys);
        model.setRemarks(remarks);
        TrackModel returnModel = trackService.addTrack(model);
        if (null != returnModel && returnModel.getTid() > 0) {
            mv.addObject("info", "修改成功");
        }else {
            mv.addObject("info", "修改失败");
        }
        mv.addObject("dto", returnModel);
        return mv;
    }
    @RequestMapping(value = "addtrackaction.do", method = RequestMethod.GET)
    public ModelAndView addAction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("track");
        mv.addObject("view", 3);
        mv.addObject("info", "");
        mv.addObject("dto", new TrackModel());
        return mv;
    }
}
