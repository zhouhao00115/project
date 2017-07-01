package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.TrackDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.mapper.TrackMapper;
import com.zhaopin.core.model.TrackModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou.hao on 2017/6/29.
 */
@Repository("TrackDao")
public class TrackDaoImpl implements TrackDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();
    @Override
    public List<TrackModel> query(TrackView view) {
        SqlSession session = sqlSessionFactory.openSession();
        List<TrackModel> trackModels = null;
        try {
            TrackMapper mapper = session.getMapper(TrackMapper.class);
            trackModels = mapper.query(view);
        } catch (Exception e) {
            e.printStackTrace();
            trackModels = new ArrayList<>();
        } finally {
            session.close();
        }
        return trackModels;
    }

    @Override
    public List<TrackModel> allTrack() {
        return null;
    }

    @Override
    public int count() {
        SqlSession session = sqlSessionFactory.openSession();
        int number ;
        try {
            TrackMapper mapper = session.getMapper(TrackMapper.class);
            number = mapper.count();
        } catch (Exception e) {
            e.printStackTrace();
            number = 0;
        } finally {
            session.close();
        }
        return number;
    }

    @Override
    public TrackModel getTrackById(int tid) {
        SqlSession session = sqlSessionFactory.openSession();
        TrackModel model ;
        try {
            TrackMapper mapper = session.getMapper(TrackMapper.class);
            model = mapper.getTrackById(tid);
        } catch (Exception e) {
            e.printStackTrace();
            model = new TrackModel();
        } finally {
            session.close();
        }
        return model;
    }

    @Override
    public TrackModel addTrack(TrackModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        TrackModel returnModel = null;
        try {
            TrackMapper mapper = session.getMapper(TrackMapper.class);
            int count = mapper.count();
            int id;
            if(count>0){
                id = mapper.getLastId();
            }else {
                id = 0;
            }
            id += 1;
            model.setTid(id);
            int rows = mapper.addTrack(model);
            if (rows > 0) {
                returnModel = mapper.getTrackById(id);
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            returnModel = new TrackModel();
        } finally {
            session.close();
        }
        return returnModel;
    }

    @Override
    public TrackModel updateTrack(TrackModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        TrackModel returnModel = null;
        try {
            TrackMapper mapper = session.getMapper(TrackMapper.class);
            int rows = mapper.updateTrack(model);
            if (rows > 0) {
                session.commit();
                return model;
            }
            returnModel = mapper.getTrackById(model.getTid());
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return returnModel;
    }
}
