package com.admin.studyJmeter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hetilong
 * @Date: 2022/1/10 19:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestInfo {

    public String url;
    public String api;
    public String port;
    public String method;
    public String protocol;
    public String contentEncoding;
    public String httpName;
    public String body;
}
