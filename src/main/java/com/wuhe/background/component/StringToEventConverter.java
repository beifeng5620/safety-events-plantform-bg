package com.wuhe.background.component;

import com.wuhe.background.entity.Event;
import com.wuhe.background.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;

/**
 * @author wuhe
 * @Date 2020/5/8 - 10:51
 * 可以直接使用@Component将自定义converter加入容器
 * 也可以在@Configuration类中使用@Bean将自定义converter加入容器
 * 解析类似 value1_value2_value3 这样的拼接字符串为Event对象
 * 例如：1_2_3_2020-05-08 11:38:50_5_6_7_8
 */
//@Component
public class StringToEventConverter implements Converter<String, Event> {
    @Override
    public Event convert(String s) {
        // 空串
        if (StringUtils.isEmpty(s)) {
            return  null;
        }

        // 不包含指定字符
        if (s.indexOf("-") == -1) {
            return null;
        }

        String[] arr = s.split("_");
        // 字符串长度不对
        if (arr.length != 8) {
            return null;
        }

        Event event = new Event();
        event.setId(arr[0]);
        event.setLng(Double.parseDouble(arr[1]));
        event.setLat(Double.parseDouble(arr[2]));
        try {
            event.setTime(DateUtil.str2date(arr[3]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setEventTypeId(arr[4]);
        event.setFlag(arr[5]);
        event.setDetails(arr[6]);
        event.setRemark(arr[7]);

        return event;
    }
}
