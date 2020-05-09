package com.wuhe.background.controller;

import com.wuhe.background.service.MapService;
import com.wuhe.background.view.RectQueryView;
import com.wuhe.background.view.SubmitEventView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuhe
 * @Date 2020/4/30 - 10:47
 * 用户端 前端请求接口处理
 */
@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/getAllMarker")
    public Object getAllMarker(RectQueryView view) {
        logger.debug("getAllMarker view = " + view);
        return mapService.getAllMarker(view);
    }

    @RequestMapping("/getAllEvent")
    public  Object getAllEvent() {
        return mapService.getAllEvent();
    }

    @RequestMapping("/submitEvent")
    public  Object submitEvent(SubmitEventView view, HttpServletRequest request) {
        view.setIp(request.getRemoteAddr());
        logger.debug("submitEvent view = " + view);
        return mapService.submitEvent(view);
    }

    @RequestMapping("/getChartsInfo")
    public  Object getChartsInfo(RectQueryView view) {
        logger.debug("getChartsInfo view = " + view);
        return mapService.getChartsInfo(view);
    }
}
