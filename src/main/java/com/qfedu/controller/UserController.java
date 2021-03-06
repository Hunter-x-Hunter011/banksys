package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/query.do")
    @ResponseBody
    public JsonResult getLoginInfo(HttpSession session){
        User u = (User)session.getAttribute(StrUtils.LOGIN_USER);
        User user = userService.selectByCode(u.getBankCode());
        JsonResult result = new JsonResult(1, user);
//        if(u != null){
//            result = new JsonResult(1, user);
//        }else{
//            result = new JsonResult(0, "用户未登陆");
//        }
        return result;
    }

    @RequestMapping("/balance.do")
    @ResponseBody
    public JsonResult getBalance(HttpSession session){
        User user = (User)session.getAttribute(StrUtils.LOGIN_USER);
        if(user == null){
            return new JsonResult(0, "未登陆");
        }

        Double balance = userService.selectBalanceByCode(user.getBankCode());
        JsonResult result = new JsonResult(1, balance);
        return result;
    }
}
