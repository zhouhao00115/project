package com.zhaopin.core.service;

import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.model.TrackModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public interface TrackService {
    public List<TrackModel> query(TrackView view);

    public TrackModel getTrackById(String tid);

    public List<TrackModel> allTrack();

    public int count();

    public TrackModel addTrack(TrackModel model);
}
