package com.wuhe.background.dao;

import com.wuhe.background.entities.Event;
import com.wuhe.background.entities.EventWeekly;
import com.wuhe.background.entities.Marker;
import com.wuhe.background.entities.Point;
import com.wuhe.background.utils.DateUtils;
import com.wuhe.background.view.RectQueryView;
import com.wuhe.background.view.SubmitEventView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        markers.put(1001,new Marker("1001",new Point(118.10388605,24.48923061),1,"纵火", new Date(),"地址：北京市东城区王府井大街88号乐天银泰百货八层"));
        markers.put(1002,new Marker("1002",new Point(118.10988608,24.48923065),2,"攻击", new Date(),"地址：厦门"));
        Long[] weeklyHappenedArr1 = new Long[]{320l, 302l, 301l, 334l, 390l, 330l, 320l};
        Long[] weeklyHappenedArr2 = new Long[]{120l, 132l, 101l, 134l, 90l, 230l, 210l};
        events = new HashMap<>();
        events.put(1,new EventWeekly("1","纵火",weeklyHappenedArr1));
        events.put(2,new EventWeekly("2","攻击",weeklyHappenedArr2));
    }

    private static String initId= "1003";

    // 保存到事件暂存 ==> 加入事件表的markers记录
    public  Object submitEvent(SubmitEventView view) {
        try {
            markers.put(Integer.valueOf(initId),new Marker(initId,new Point(view.getLng(),view.getLat()),view.getEventType(),(events.get(view.getEventType())).getName(), DateUtils.str2date(view.getTime()),view.getDetails()));
            Integer tmp = Integer.valueOf(initId);
            tmp++;
            initId = tmp.toString();
            return  "保存成功";
        } catch (ParseException e) {
            e.printStackTrace();
            return "保存失败";
        }

    }


    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询一周内所有marker
    public List<Marker> getAllMarker(RectQueryView view) {
        String queryWeekly =
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
                "\tAND DATE_SUB( CURDATE(), INTERVAL 7 DAY ) < date( time ) \n";
        Object[] obj = null;
        if (!view.isEmpty()) {
            queryWeekly +=
                    "\tAND a.lng >= ? \n" +
                    "\tAND a.lat <= ? \n" +
                    "\tAND a.lng <= ? \n" +
                    "\tAND a.lat >= ? \n" ;
            obj = new Object[]{view.getTlLng(),view.getTlLat(),view.getBrLng(),view.getBrLat()};
        }
        return jdbcTemplate.query(queryWeekly,obj,(RowMapper) (rs, rowNum) -> new Marker(rs.getString("id"),rs.getDouble("lng"),
                rs.getDouble("lat"),rs.getInt("type"),rs.getString("type_name"),
                rs.getTimestamp("time"),rs.getString("details")));
    }

    // 查询所有event类型
    public List<Event> getAllEvent() {
        String sql = "SELECT\n" +
                "\tid AS id,\n" +
                "\tevent_name AS `name`\n" +
                "FROM\n" +
                "\tevent_type";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Event.class));
    }

    // 查询图表信息(一周)
    public List<EventWeekly> getChartsInfo(RectQueryView view) {
        String rect = "";
        Object[] obj = null;
        if (!view.isEmpty()) {
            rect += "\t\tAND a.lng >= ? \n" +
                    "\t\tAND a.lat <= ? \n" +
                    "\t\tAND a.lng <= ? \n" +
                    "\t\tAND a.lat >= ? \n" ;
            obj = new Object[]{view.getTlLng(),view.getTlLat(),view.getBrLng(),view.getBrLat()};
        }
        String queryWeekly = "SELECT\n" +
                "\ttmp.id,\n" +
                "\ttmp.`name`,\n" +
                "\ttmp.date,\n" +
                "\tDAYOFWEEK( tmp.date ) - 1 AS day_of_week,\n" +
                "\tcount(*) AS count \n" +
                "FROM\n" +
                "\t(\n" +
                "\tSELECT\n" +
                "\t\ta.event_type_id AS id,\n" +
                "\t\tb.event_name AS `name`,\n" +
                "\t\tDATE( a.time ) AS date \n" +
                "\tFROM\n" +
                "\t\t`event` a\n" +
                "\t\tLEFT JOIN event_type b ON a.event_type_id = b.id \n" +
                "\tWHERE\n" +
                "\t\ta.flag = '0' \n" +
                "\t\tAND DATE_SUB( CURDATE(), INTERVAL 7 DAY ) < date( time ) \n" +
                    rect +
                "\tORDER BY\n" +
                "\t\tdate DESC \n" +
                "\t) tmp \n" +
                "GROUP BY\n" +
                "\ttmp.id,\n" +
                "\ttmp.`name`,\n" +
                "\ttmp.date \n" +
                "ORDER BY\n" +
                "\tdate DESC,\n" +
                "\tcount DESC";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(queryWeekly,obj);
        // 统计本周发生了多少种事件类型
        List<String> arrayList = new ArrayList<String>();
        for (int i = 0;i < list.size();i++) {
            String id = (String) list.get(i).get("id");
            if (!arrayList.contains(id)) {
                arrayList.add(id);
            }
        }
        // 统计事件周一到周日的发生数量
        List<EventWeekly> eventWeeklies = new ArrayList<EventWeekly>();
        Integer happenedTypeCnt = arrayList.size();
        // 标记事件类型是否被添加到eventklies中
        Boolean[] added = new Boolean[happenedTypeCnt];
        for (int i = 0;i < happenedTypeCnt;i++) {
            added[i] = false;
        }
        // 初始化周一到周天
        for (int i = 0;i < list.size();i++) {
            for (int j = 0;j < happenedTypeCnt;j++) {
                if (list.get(i).get("id").equals(arrayList.get(j))) {
                    if (added[j] == false) {
                        eventWeeklies.add(new EventWeekly((String) list.get(i).get("id"),
                                (String) list.get(i).get("name"),
                                new Long[]{0l, 0l, 0l, 0l, 0l, 0l, 0l}));
                        added[j] = true;
                        break;
                    }
                }
            }

        }
        // 设置周一到周天
        for (int i = 0;i < list.size();i++) {
            for (int j = 0;j < happenedTypeCnt;j++) {
                if (list.get(i).get("id").equals(eventWeeklies.get(j).getId())) {
                    Long[] weeklyHappened = eventWeeklies.get(j).getWeeklyHappened();
                    weeklyHappened[(int) list.get(i).get("day_of_week")] = (Long) list.get(i).get("count");
                    eventWeeklies.get(j).setWeeklyHappened(weeklyHappened);
                }
            }

        }
        return eventWeeklies;
    }
}
