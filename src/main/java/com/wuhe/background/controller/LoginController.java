package com.wuhe.background.controller;

import com.wuhe.background.entity.SysAdmin;
import com.wuhe.background.service.BackGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author wuhe
 * @create 2019/9/29 0029-上午 8:27
 */
@Controller
public class LoginController {

    @Autowired
    BackGroundService backGroundService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Map<String,Object> map,
                        HttpSession session){
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            SysAdmin sysAdminByAccount = backGroundService.getSysAdminByAccount(username);
            if (null != sysAdminByAccount) {
                if (sysAdminByAccount.getPassword().equals(password)) {
                    //为了防止表单重复提交。使用重定向
                    session.setAttribute("loginUser",username);
                    String uid = sysAdminByAccount.getId();
                    session.setAttribute("loginUserId", uid);
                    return "redirect:/main.html";
                } else {
                    map.put("msg","用户名密码错误");
                    return "login";
                }

            } else {
                map.put("msg","用户不存在");
                return "login";
            }

        }else {
            map.put("msg","用户名密码不能为空");
            return "login";
        }
    }

    @GetMapping(value = "/user/logout")
    public String login(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "login";
    }
}
