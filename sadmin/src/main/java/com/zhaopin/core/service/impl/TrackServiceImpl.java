package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.TrackDao;
import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.model.TrackModel;
import com.zhaopin.core.service.TrackService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
@Service("TrackService")
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackDao trackDao;

    @Override
    public List<TrackModel> query(TrackView view) {
        return trackDao.query(view);
    }

    @Override
    public TrackModel getTrackById(String tid) {
        if (StringUtil.isNullOrEmpty(tid)) {
            return new TrackModel();
        }
        int number;
        try {
            number = Integer.parseInt(tid);
        } catch (Exception e) {
            e.printStackTrace();
            return new TrackModel();
        }
        return trackDao.getTrackById(number);
    }

    @Override
    public List<TrackModel> allTrack() {
        return trackDao.allTrack();
    }

    @Override
    public int count() {
        return trackDao.count();
    }

    @Override
    public TrackModel addTrack(TrackModel model) {
        if (model.getTid() > 0) {
            return trackDao.updateTrack(model);
        }
        return trackDao.addTrack(model);
    }
}
