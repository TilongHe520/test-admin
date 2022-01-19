package com.admin.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @Author: hetilong
 * @Date: 2022/1/3 11:19
 */
@Data
public class CurlParams {
    public String url;
    public Map<String, String> header;
    private String data;
}
