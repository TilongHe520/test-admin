package com.admin.controller;

import com.admin.common.CommonResult;
import com.admin.pojo.StressVO;
import com.admin.pojo.UITestInfo;
import com.admin.pojo.UserInfo;
import com.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult login(@RequestBody UserInfo user) {

        UserInfo userInfo = userService.findByUsername(user.getUserName());
        if (null != userInfo) {
            if (user.getPassword().equals(userInfo.getPassword())) {
                log.info(user.toString());
                return CommonResult.success(user, "登录成功");
            } else {
                log.error(user.toString());
                return CommonResult.validateFailed("用户名或密码错误");
            }
        } else {
            log.error(user.toString());
            return CommonResult.failed("用户不存在");
        }

    }

    @RequestMapping(value = "/api/uitest", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult getUIInfo(@RequestBody UITestInfo uiTestInfo) {
        System.out.println(uiTestInfo.toString());
        return CommonResult.success(uiTestInfo);
    }

    @RequestMapping(value = "/api/stress", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult getStressInfo(@RequestBody StressVO stressVO) {
        System.out.println(stressVO.toString());
        return CommonResult.success(stressVO);
    }

    @RequestMapping(value = "/api/user/userList")
    @CrossOrigin
    public CommonResult getUserInfo() {
        List<UserInfo> userInfos = userService.findAll();
        return CommonResult.success(userInfos);
    }
}
