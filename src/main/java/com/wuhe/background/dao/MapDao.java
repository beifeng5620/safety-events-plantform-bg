package com.wuhe.background.dao;

import com.wuhe.background.entities.EventWeekly;
import com.wuhe.background.entities.Marker;
import com.wuhe.background.entities.Point;
import com.wuhe.background.utils.DateUtils;
import com.wuhe.background.view.RectQueryView;
import com.wuhe.background.view.SubmitEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * 根据区域范围查询地图标注
 * @author wuhe
 * @Date 2020/4/30 - 14:30
 */
@Repository
public class MapDao {

    private static Map<Integer, Marker> markers = null;
    private static Map<Integer, EventWeekly> events= null;

    static {
        markers = new HashMap<>();
        markers.put(1001,new Marker(1001,new Point(118.10388605,24.48923061),1,"纵火", new Date(),"地址：北京市东城区王府井大街88号乐天银泰百货八层"));
        markers.put(1002,new Marker(1002,new Point(118.10988608,24.48923065),2,"攻击", new Date(),"地址：厦门"));
        Integer[] weeklyHappenedArr1 = new Integer[]{320, 302, 301, 334, 390, 330, 320};
        Integer[] weeklyHappenedArr2 = new Integer[]{120, 132, 101, 134, 90, 230, 210};
        events = new HashMap<>();
        events.put(1,new EventWeekly(1,"纵火",weeklyHappenedArr1));
        events.put(2,new EventWeekly(2,"攻击",weeklyHappenedArr2));
    }

    private static Integer initId= 1003;
    // 查询所有marker
//    public Collection<Marker> getAllMarker() {
//        return markers.values();
//    }
    // 查询所有event
    public Collection<EventWeekly> getAllEvent() {return events.values();}

    // 保存到事件暂存 ==> 加入事件表的markers记录
    public  Object submitEvent(SubmitEventView view) {
        try {
            markers.put(initId,new Marker(initId++,new Point(view.getLng(),view.getLat()),view.getEventType(),(events.get(view.getEventType())).getName(), DateUtils.str2date(view.getTime()),view.getDetails()));
            return  "保存成功";
        } catch (ParseException e) {
            e.printStackTrace();
            return "保存失败";
        }

    }

    // 查询图表信息
    public Collection<EventWeekly> getChartsInfo() {
        return events.values();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询一周内所有marker
    public List getAllMarker(RectQueryView view) {
        StringBuffer queryWeekly = new StringBuffer(
                "SELECT\n" +
                "\ta.id AS id,\n" +
                "\ta.lng AS lng,\n" +
                "\ta.lat AS lat,\n" +
                "\ta.event_type_id AS type,\n" +
                "\tb.event_name AS type_name,\n" +
                "\ta.time AS time,\n" +
                "\ta.details AS details \n" +
                "FROM\n" +
                "\t`event` a\n" +
                "\tLEFT JOIN event_type b ON a.event_type_id = b.id \n" +
                "WHERE\n" +
                "\ta.flag = '0' \n" +
                "\tAND DATE_SUB( CURDATE(), INTERVAL 7 DAY ) <= date( time ) \n" );
        Object[] obj = null;
        if (!view.isEmpty()) {
            queryWeekly.append(
                    "\tAND a.lng >= ? \n" +
                    "\tAND a.lat <= ? \n" +
                    "\tAND a.lng <= ? \n" +
                    "\tAND a.lat >= ? \n" );
            obj = new Object[]{view.getTlLng(),view.getTlLat(),view.getBrLng(),view.getBrLat()};
        }
        return jdbcTemplate.query(queryWeekly.toString(),obj,(RowMapper) (rs, rowNum) -> new Marker(rs.getInt("id"),rs.getDouble("lng"),
                rs.getDouble("lat"),rs.getInt("type"),rs.getString("type_name"),
                rs.getTimestamp("time"),rs.getString("details")));
    }
}
