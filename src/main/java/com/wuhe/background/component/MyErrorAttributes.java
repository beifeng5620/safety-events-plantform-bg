package com.wuhe.background.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author wuhe
 * @create 2019/9/30 0030-下午 2:43
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","wuhe");
        // ext是自定义异常产生的数据 需要判断一下空值
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        if (null != ext) {
            map.put("ext",ext);
        }
        return map;
    }
}
