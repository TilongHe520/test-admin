package com.admin.controller;

import com.admin.common.CommonResult;
import com.admin.pojo.UserInfo;
import com.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult login(@RequestBody UserInfo user) {

        if (user.getUserName().equals("admin") && user.getPassword().equals("123456")) {
            log.info(user.toString());
            return CommonResult.success(user, "登录成功");
        } else{
            log.error(user.toString());
            return CommonResult.validateFailed("用户名或密码错误");
        }

    }

    @GetMapping(value = "/find")
    public String find(){
        return userService.findByUsername("hetilong").toString();
    }
}
