package com.admin.controller;

import com.admin.common.CommonResult;
import com.admin.pojo.UserInfo;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult login(@RequestBody UserInfo user) {
        System.out.println(user.toString());
        if (user.getUserName().equals("admin") && user.getPassword().equals("123456"))
            return CommonResult.success(user,"登录成功");
        else
            return CommonResult.validateFailed("用户名或密码错误");
    }
}
