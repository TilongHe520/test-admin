package com.admin.service;

import com.admin.pojo.CurlParams;
import com.admin.util.ResolveCurl;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @Author: hetilong
 * @Date: 2022/1/3 11:13
 */
public class InterfaceResService {

    /**
     * @param curl
     */
    public String getResponse(String curl) {

        ResolveCurl rc = new ResolveCurl(curl);
        CurlParams cp = rc.getParams();

        Map<String, String> map = cp.getHeader();

        String res = null;

        if (cp.getData() == null) {

            map.remove("Content-Length");

            res = given().headers(map).post(cp.getUrl()).asString();

        } else {
            res = given().headers(cp.getHeader()).body(cp.getData()).post(cp.getUrl()).asString();

        }
        if (res == null) {
            return null;
        }

        return res;
    }

    @Test
    public void test01() {
        String curl1 = "curl 'http://hkpos.dev.maoyan.team/api/event/list?locale=zh_CN' \\   -H 'Connection: keep-alive' \\   -H 'Accept: application/json' \\   -H 'x-terminal-code: terminal-test' \\   -H 'x-terminal-id: 91' \\   -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36' \\   -H 'Content-Type: application/json;charset=UTF-8' \\   -H 'Origin: http://hkpos.dev.maoyan.team' \\   -H 'Referer: http://hkpos.dev.maoyan.team/seller/telephone_ticketing/list' \\   -H 'Accept-Language: zh-CN,zh;q=0.9' \\   -H 'Cookie: pos-token=eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJoa1Nob3ciLCJpYXQiOjE2NDE5ODcyNDIsInN5c3RlbUNvZGUiOjIsInJlbmV3YWwiOmZhbHNlLCJsb2dpbkZsYWciOiIxMzYyMTY0MTk4NzI0MjMzMyIsInRlcm1pbmFsSWQiOjkxLCJ0ZXJtaW5hbENvZGUiOiJ0ZXJtaW5hbC10ZXN0IiwidXNlcklkIjoxMzZ9.ZK_XuvmTVj6QI07Cau9KGHZmDFiTlG9A_y-EclepQI4; locale=zh-CN' \\   --data-raw '{\"pageNo\":0,\"pageSize\":10,\"salesScheduleSort\":1,\"tab\":\"LATEST\",\"roleId\":53,\"userId\":136,\"menu\":\"TELEPHONE_PURCHASE\",\"menuType\":7}' \\   --compressed \\   --insecure\n";

        System.out.println(getResponse(curl1));
    }
}
