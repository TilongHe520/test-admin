package com.admin.controller;

import com.admin.common.CommonResult;
import com.admin.pojo.CurlParams;
import com.admin.util.ResolveCurl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;


import static io.restassured.RestAssured.given;

/**
 * @Author: hetilong
 * @Date: 2022/1/3 11:11
 */

@RestController
public class InterfaceResController {


    @RequestMapping(value = "/api/interfaceRes", method = RequestMethod.POST)
    @CrossOrigin
    public CommonResult getResponse(@RequestBody String curlData) {
        JSONObject jsonObject = JSONObject.parseObject(curlData);
        String curl = jsonObject.getString("curlData");
        ResolveCurl rc = new ResolveCurl(curl);
        CurlParams cp = rc.getParams();

        String res = given().headers(cp.getHeader()).body(cp.getData()).post(cp.getUrl()).asString();
        return CommonResult.success(res);
    }
}
