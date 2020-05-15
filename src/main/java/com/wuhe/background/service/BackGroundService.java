package com.wuhe.background.service;

import com.wuhe.background.dao.BackGroundDao;
import com.wuhe.background.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:02
 */
@Service
public class BackGroundService {

    @Autowired
    BackGroundDao backGroundDao;

    // 查询所有
    public List<BlackList> getBlackLists() {
        return backGroundDao.getBlackLists();
    }

    public List<Event> getEvents() {
        return backGroundDao.getEvents();
    }

    public List<EventTmp> getEventTmps() {
        return backGroundDao.getEventTmps();
    }

    public List<EventType> getEventTypes() {
        return backGroundDao.getEventTypes();
    }

    public List<SysAdmin> getSysAdmins() {
        return backGroundDao.getSysAdmins();
    }

    public List<SysAdminLog> getSysAdminLogs() {
        return backGroundDao.getSysAdminLogs();
    }

    // 根据ID查询
    public Event getEventById(String id) {
        List<Event> eventById = backGroundDao.getEventById(id);
        return eventById.size()==0?null:eventById.get(0);
    }

    public EventTmp getEventTmpById(String id) {
        List<EventTmp> eventTmpById = backGroundDao.getEventTmpById(id);
        return eventTmpById.size()==0?null:eventTmpById.get(0);
    }

    public EventType getEventTypeById(String id) {
        List<EventType> eventTypeById = backGroundDao.getEventTypeById(id);
        return eventTypeById.size()==0?null:eventTypeById.get(0);
    }

    public SysAdmin getSysAdminById(String id) {
        List<SysAdmin> sysAdminById = backGroundDao.getSysAdminById(id);
        return sysAdminById.size()==0?null:sysAdminById.get(0);
    }

    public SysAdmin getSysAdminByAccount(String account) {
        List<SysAdmin> sysAdminById = backGroundDao.getSysAdminByAccount(account);
        return sysAdminById.size()==0?null:sysAdminById.get(0);
    }


    // 根据ID删除
    public int deleteEventById(String id) {
        return backGroundDao.deleteEventById(id);
    }

    public int deleteEventTmpById(String id) {
        return backGroundDao.deleteEventTmpById(id);
    }

    public int deleteEventTypeById(String id) {
        return backGroundDao.deleteEventTypeById(id);
    }

    public int deleteSysAdminById(String id) {
        return backGroundDao.deleteSysAdminById(id);
    }

    // 改、存
    public int saveOrUpdateEvent(Event event){
        return backGroundDao.saveOrUpdateEvent(event);
    }

    public int saveOrUpdateEventType(EventType eventType) {
        return backGroundDao.saveOrUpdateEventType(eventType);
    }

    public int updateEventTmp(EventTmp eventTmp) {
        return backGroundDao.updateEventTmp(eventTmp);
    }

    public int saveOrUpdateSysAdmin(SysAdmin sysAdmin) {
        return backGroundDao.saveOrUpdateSysAdmin(sysAdmin);
    }
}
