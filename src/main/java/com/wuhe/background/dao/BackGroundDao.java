package com.wuhe.background.dao;

import com.wuhe.background.entity.*;
import com.wuhe.background.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:02
 */
@Repository
public class BackGroundDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询所有
    public List<BlackList> getBlackLists() {
        return jdbcTemplate.query("select * from black_list",new BeanPropertyRowMapper<>(BlackList.class));
    }

    public List<Event> getEvents() {
        return jdbcTemplate.query("select * from event",new BeanPropertyRowMapper<>(Event.class));
    }

    public List<EventTmp> getEventTmps() {
        return jdbcTemplate.query("select * from event_tmp where flag = '0'",new BeanPropertyRowMapper<>(EventTmp.class));
    }

    public List<EventType> getEventTypes() {
        return jdbcTemplate.query("select * from event_type",new BeanPropertyRowMapper<>(EventType.class));
    }

    public List<SysAdmin> getSysAdmins() {
        return jdbcTemplate.query("select * from sys_admin",new BeanPropertyRowMapper<>(SysAdmin.class));
    }

    public List<SysAdminLog> getSysAdminLogs() {
        return jdbcTemplate.query("select * from sys_admin_log",new BeanPropertyRowMapper<>(SysAdminLog.class));
    }

    // 根据ID查询
    public List<Event> getEventById(String id) {
        return jdbcTemplate.query("select * from event where id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Event.class));
    }

    public List<EventTmp> getEventTmpById(String id) {
        return jdbcTemplate.query("select * from event_tmp where id = ?",new Object[]{id},new BeanPropertyRowMapper<>(EventTmp.class));
    }

    public List<EventType> getEventTypeById(String id) {
        return jdbcTemplate.query("select * from event_type where id = ?",new Object[]{id},new BeanPropertyRowMapper<>(EventType.class));
    }

    public List<SysAdmin> getSysAdminById(String id) {
        return jdbcTemplate.query("select * from sys_admin where id = ?",new Object[]{id},new BeanPropertyRowMapper<>(SysAdmin.class));
    }

    public List<SysAdmin> getSysAdminByAccount(String account) {
        return jdbcTemplate.query("select * from sys_admin where account = ?",new Object[]{account},new BeanPropertyRowMapper<>(SysAdmin.class));
    }

    // 根据ID删除
    public int deleteEventById(String id) {
        return jdbcTemplate.update("delete from event where id = ?",new Object[]{id});
    }

    public int deleteEventTmpById(String id) {
        return jdbcTemplate.update("delete from event_tmp where id = ?",new Object[]{id});
    }

    public int deleteEventTypeById(String id) {
        return jdbcTemplate.update("delete from event_type where id = ?",new Object[]{id});
    }

    // 存、改
    public int saveOrUpdateEvent(Event event) {
        if(event.getId() == null){
            event.setId(IDUtil.uuid());
            return jdbcTemplate.update("insert into event values (?,?,?,?,?,?,?,?)",new Object[]{event.getId(),event.getLng(),
                event.getLat(),event.getTime(),event.getEventTypeId(),event.getFlag(),event.getDetails(),event.getRemark()});
        } else {
            return jdbcTemplate.update("update event set lng = ?,lat = ?,time = ?,event_type_id = ?,flag = ?,details = ?,remark = ? where id = ?",
                    new Object[]{event.getLng(),event.getLat(),event.getTime(),event.getEventTypeId(),
                            event.getFlag(),event.getDetails(),event.getRemark(),event.getId()});
        }
    }

    public int saveOrUpdateEventType(EventType eventType) {
        if(eventType.getId() == null){
            eventType.setId(IDUtil.uuid());
            return jdbcTemplate.update("insert into event_type values (?,?)",new Object[]{eventType.getId(),eventType.getEventName()});
        } else {
            return jdbcTemplate.update("update event_type set event_name = ? where id = ?",new Object[]{eventType.getEventName(),eventType.getId()});
        }
    }

    public int updateEventTmp(EventTmp eventTmp) {
        return jdbcTemplate.update("update event_tmp set lng = ?,lat = ?,time = ?,event_type_id = ?,contact = ?,ip = ?,flag = ?,details = ? where id = ?",
                    new Object[]{eventTmp.getLng(),eventTmp.getLat(),eventTmp.getTime(),eventTmp.getEventTypeId(),
                            eventTmp.getContact(),eventTmp.getIp(),eventTmp.getFlag(),eventTmp.getDetails(),eventTmp.getId()});
    }
}
