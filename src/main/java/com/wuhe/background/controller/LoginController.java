package com.wuhe.background.controller;

import com.wuhe.background.entity.SysAdmin;
import com.wuhe.background.service.BackGroundService;
import com.wuhe.background.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    @PostMapping("/user/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String code,
                        Map<String,Object> map,
                        HttpSession session) {
        String verify_code = (String) session.getAttribute("verify_code");
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(verify_code) ||
                !verify_code.toLowerCase().equals(code.toLowerCase())) {
            map.put("msg","验证码错误");
            return "login";
        }

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            SysAdmin sysAdminByAccount = backGroundService.getSysAdminByAccount(username);
            if (null != sysAdminByAccount) {
                if (sysAdminByAccount.getPassword().equals(password)) {
                    //为了防止表单重复提交。使用重定向
                    session.setAttribute("loginUser",username);
                    String uid = sysAdminByAccount.getId();
                    session.setAttribute("loginUserId", uid);
                    return "redirect:/main.html";
                }
                map.put("msg","用户名密码错误");
                return "login";
            }
            map.put("msg","用户不存在");
            return "login";
        }
        map.put("msg","用户名密码不能为空");
        return "login";
    }

    @GetMapping("/user/logout")
    public String login(HttpSession session){
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "login";
    }

    @GetMapping("/user/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCodeUtil code = new VerificationCodeUtil();
        BufferedImage image = code.getImage();
        String text = code.getText();
        session.setAttribute("verify_code",text);
        VerificationCodeUtil.output(image,resp.getOutputStream());
    }
}
