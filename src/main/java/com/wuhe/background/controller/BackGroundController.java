package com.wuhe.background.controller;

import com.wuhe.background.entity.Event;
import com.wuhe.background.entity.EventTmp;
import com.wuhe.background.entity.EventType;
import com.wuhe.background.entity.SysAdmin;
import com.wuhe.background.service.BackGroundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wuhe
 * @Date 2020/5/7 - 13:59
 */
@Controller
public class BackGroundController {

    @Autowired
    BackGroundService backGroundService;

    //查询所有事件返回列表页面
    //model map modelmap参数都在请求域中
    @GetMapping("/events")
    public String toEventListPage(Model model){
        List<Event> events = backGroundService.getEvents();

        //放在请求域中
        model.addAttribute("events",events);
        return "event/list";
    }

    //来到事件添加页面
    @GetMapping("/event")
    public String toEventAddPage(Model model){
        //来到添加页面，查出所有的事件类型，在页面显示
        List<EventType> eventTypes = backGroundService.getEventTypes();
        model.addAttribute("eventTypes",eventTypes);
        return "event/add";
    }

    //事件添加
    //SpringMvc自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/event")
    public String addEvent(Event event){
        backGroundService.saveOrUpdateEvent(event);
        System.out.println("event = " + event);
        //来到事件列表页面
        //redirect:重定向 /代表当前项目路径 相当于客户端行为
        //forward:转发 地址栏URL不变 相当于服务端行为
        return "redirect:/events";
    }

    //来到事件修改页面 查出当前事件 在页面回显
    @GetMapping("/event/{id}")
    public String toEventEditPage(@PathVariable("id") String id, Model model){
        Event eventById = backGroundService.getEventById(id);

        //既然是修改事件信息，肯定是要显示所有事件类型的，不然咋修改事件类型
        List<EventType> eventTypes = backGroundService.getEventTypes();
        model.addAttribute("event",eventById);
        model.addAttribute("eventTypes",eventTypes);

        //回到修改页面(add页面是修改添加2合1的页面)
        return "event/add";
    }

    //事件修改
    @PutMapping("/event")
    public String updateEvent(Event event){
        System.out.println("event = " + event);
        backGroundService.saveOrUpdateEvent(event);

        return "redirect:/events";
    }

    //事件删除
    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable("id") String id){
        backGroundService.deleteEventById(id);
        return "redirect:/events";
    }

    //来到事件添加页面
    @GetMapping("/eventBatchAdd")
    public String toEventBatchAddPage(Model model){
        return "event/batchAdd";
    }

//**************************事件提交待审核部分********************************
    @GetMapping("/eventTmps")
    public String toEventTmpListPage(Model model){
        List<EventTmp> eventTmps = backGroundService.getEventTmps();

        model.addAttribute("eventTmps",eventTmps);
        return "eventTmp/list";
    }

    @GetMapping("/eventTmp/{id}")
    public String toEventTmpAuditPage(@PathVariable("id") String id, Model model){
        EventTmp eventTmpById = backGroundService.getEventTmpById(id);

        List<EventType> eventTypes = backGroundService.getEventTypes();
        model.addAttribute("eventTmp",eventTmpById);
        model.addAttribute("eventTypes",eventTypes);
        return "eventTmp/audit";
    }

    @PutMapping("/eventTmp")
    public String updateEventTmp(EventTmp eventTmp){
        System.out.println("eventTmp = " + eventTmp);
        backGroundService.updateEventTmp(eventTmp);

        // FIXME 审核通过后还需要加入到event表中
        Event event = new Event();
        BeanUtils.copyProperties(eventTmp,event);
        // 不给ID赋值，让其自动生成，否则在DAO层就会更新id关联的记录
        event.setId(null);
        backGroundService.saveOrUpdateEvent(event);
        return "redirect:/eventTmps";
    }

    @DeleteMapping("/eventTmp/{id}")
    public String deleteEventTmp(@PathVariable("id") String id){
        backGroundService.deleteEventTmpById(id);
        return "redirect:/eventTmps";
    }

//**************************事件类型部分********************************
    @GetMapping("/eventTypes")
    public String toEventTypeListPage(Model model){
        List<EventType> eventTypes = backGroundService.getEventTypes();
        model.addAttribute("eventTypes",eventTypes);
        return "eventType/list";
    }

    @GetMapping("/eventType")
    public String toEventTypeAddPage(){
        return "eventType/add";
    }

    @PostMapping("/eventType")
    public String addEventType(EventType eventType){
        backGroundService.saveOrUpdateEventType(eventType);
        System.out.println("eventType = " + eventType);
        return "redirect:/eventTypes";
    }

    @GetMapping("/eventType/{id}")
    public String toEventTypeEditPage(@PathVariable("id") String id, Model model){
        EventType eventTypeById = backGroundService.getEventTypeById(id);
        model.addAttribute("eventType",eventTypeById);
        return "eventType/add";
    }

    @PutMapping("/eventType")
    public String updateEventType(EventType eventType){
        System.out.println("eventType = " + eventType);
        backGroundService.saveOrUpdateEventType(eventType);
        return "redirect:/eventTypes";
    }

    @DeleteMapping("/eventType/{id}")
    public String deleteEventType(@PathVariable("id") String id){
        backGroundService.deleteEventTypeById(id);
        return "redirect:/eventTypes";
    }

//**************************批量导入事件********************************
    @ResponseBody
    @RequestMapping("/batchImportEvents/{uid}")
    public String batchImportEvents(@PathVariable("uid") String id,@RequestParam("events") List<Event> events) {
        SysAdmin sysAdmin = backGroundService.getSysAdminById(id);
        if (null == sysAdmin) {
            return "添加失败，没有权限";
        }

        boolean hasEmptyProperties = false;
        if (null == events) {
            hasEmptyProperties = true;
        } else {
            for (Event event : events) {
                if (event.isEmpty()) {
                    hasEmptyProperties = true;
                    break;
                }
                event.setId(null);
            }
        }

        if (hasEmptyProperties) {
            return "添加失败，含有空的必填字段";
        } else {
            for (Event event : events) {
                backGroundService.saveOrUpdateEvent(event);
            }
            return "添加成功";
        }
    }
}
