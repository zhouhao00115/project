package com.zhaopin.core.dao;

import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.model.TrackModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
public interface TrackDao {
    public List<TrackModel> query(TrackView view);

    public List<TrackModel> allTrack();

    public int count();

    public TrackModel getTrackById(int tid);

    public TrackModel addTrack(TrackModel model);

    public TrackModel updateTrack(TrackModel model);
}
