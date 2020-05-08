package com.wuhe.background.component;

import com.wuhe.background.entity.Event;
import com.wuhe.background.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author wuhe
 * @Date 2020/5/8 - 14:53
 * 解析[{k:v,k:v,k:v},{k:v,k:v,k:v}]这样的json字符串为List<Event>对象
 */
public class StringToEventConverterV2 implements Converter<String, List<Event>> {
    @Override
    public List<Event> convert(String s) {
        // 空串
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        // 过滤空白字符
        s = s.replaceAll("\\s*|\t|\r|\n","");
        // 不包含指定字符
        if (s.indexOf("[") == -1 || s.indexOf("]") == -1 ||
                s.indexOf("{") == -1 || s.indexOf("}") == -1) {
            return null;
        }
        // 去掉前后[]括号
        // 变为{},{},{},{}
        String substring = s.substring(2, s.length() - 2);

        // 然后以"},"为分割符，分割
        // 变为String["{...","{...","{...","{...}"]
        String[] split1 = substring.split("},");

        // 所有项去除首位"{"
        for (String s1 : split1) {
            s1 = s1.substring(2,s1.length()-1);
        }
        // 最后一项去除末位"}"
        // 变为 String["...","...","...","..."],其中"..."是"k:v,k:v,k:v"
        int lastStr = split1.length - 1;
        split1[lastStr] = split1[lastStr].substring(1,split1[lastStr].length() -1 );

        // 解析并赋值
        List<Event> events = new ArrayList<>();
        for (int i = 0;i < split1.length;i++) {
            // 得到k-v键值对
            String[] fields = StringUtils.commaDelimitedListToStringArray(split1[i]);
            HashMap<String, String> propertyKVMap = new HashMap<>();
            for (String field : fields) {
                // 以":"分割会导致时间字符串也被错误分割
                // 所以日期中的":" 不作为分割符
                if (field.substring(0,5).equals("time:")) {
                    // 不把":"放进去 并去除引号
                    propertyKVMap.put(field.substring(0,4), field.substring(5).replaceAll("\"",""));
                } else {
                    String[] split = field.split(":");
                    // 去除引号
                    propertyKVMap.put(split[0], split[1].replaceAll("\"",""));
                }
            }
            // 属性赋值
            Event event = new Event();
            Class<? extends Event> clz = event.getClass();
            Field[] declaredFields = clz.getDeclaredFields();
            for (Field field : declaredFields) {
                String propertyName = field.getName();
                String propertyValue = propertyKVMap.get(propertyName);
                if (null != propertyValue) {
                    try {
                        PropertyDescriptor pd = null;
                        pd = new PropertyDescriptor(propertyName, clz);
                        Class<?> propertyType = pd.getPropertyType();
                        Method method = pd.getWriteMethod();
                        if (method != null) {
                            if (Date.class == propertyType) {
                                Date date = DateUtil.str2date4EventConverterV2(propertyValue);
                                method.invoke(event, date);
                            } else if (Double.class == propertyType) {
                                Double aDouble = Double.valueOf(propertyValue);
                                method.invoke(event, aDouble);
                            } else {
                                method.invoke(event, propertyValue);
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            events.add(event);
        }
        return events;
    }
}
