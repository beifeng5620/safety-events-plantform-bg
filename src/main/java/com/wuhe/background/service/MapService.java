package com.wuhe.background.service;

import com.wuhe.background.dao.MapDao;
import com.wuhe.background.entities.Event;
import com.wuhe.background.entities.EventWeekly;
import com.wuhe.background.entities.Marker;
import com.wuhe.background.view.RectQueryView;
import com.wuhe.background.view.SubmitEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuhe
 * @Date 2020/4/30 - 14:26
 */
@Service
public class MapService {
    @Autowired
    private MapDao mapDao;

    public Object getAllMarker(RectQueryView view) {
//        Collection<Marker> markers = mapDao.getAllMarker();
        List<Marker> markers = mapDao.getAllMarker(view);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("count", markers.size());
        hashMap.put("markers", markers);
        return hashMap;
    }

    public  Object getAllEvent() {
        Collection<EventWeekly> events = mapDao.getAllEvent();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("count", events.size());
        hashMap.put("type", events);
        return hashMap;
    }

    public  Object submitEvent(SubmitEventView view) {
        return mapDao.submitEvent(view);
    }

    public  Object getChartsInfo() {
        Collection<EventWeekly> events = mapDao.getChartsInfo();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("count", events.size());
        hashMap.put("type", events);
        return hashMap;
    }
}
