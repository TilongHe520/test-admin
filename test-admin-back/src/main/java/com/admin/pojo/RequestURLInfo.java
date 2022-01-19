package com.admin.pojo;

import lombok.Data;

import java.util.List;

@Data
public class RequestURLInfo {
    private int id;
    private String apiCode;
    private String secondUrL;
    private String secondUrlParams;
    private String requestMethod;
    private String remark;

    private List<RequestParamsInfo> paramsInfo;

    public RequestURLInfo(String apiCode, String secondUrL, String secondUrlParams,
                          String requestMethod, String remark, List<RequestParamsInfo> paramsInfo) {
        this.apiCode = apiCode;
        this.secondUrL = secondUrL;
        this.secondUrlParams = secondUrlParams;
        this.requestMethod = requestMethod;
        this.remark = remark;
        this.paramsInfo = paramsInfo;
    }
}
