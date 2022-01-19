package com.admin.controller;

import com.admin.common.CommonResult;
import com.admin.pojo.RequestParamsInfo;
import com.admin.service.RequestParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestParamsController {

    @Autowired
    private RequestParamsService requestParamsService;

    @GetMapping(value = "/api/batchInsert")
    public CommonResult batchInsert() {
        List<RequestParamsInfo> paramsInfos = new ArrayList<>();
        paramsInfos.add(new RequestParamsInfo("ad", "A", "a"));
        paramsInfos.add(new RequestParamsInfo("ad1", "A1", "a"));
        paramsInfos.add(new RequestParamsInfo("ad2", "A2", "a"));

        int a = requestParamsService.batchInsert(paramsInfos);
        if (a > 0) {
            return CommonResult.success(a, "插入成功");
        } else {
            return CommonResult.success(paramsInfos, "插入失败");
        }

    }
}
