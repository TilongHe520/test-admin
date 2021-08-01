package com.admin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestParamsInfo {
    private int id;
    private String requestVariable;
    private String requestParams;
    private String apiCode;

    public RequestParamsInfo(String requestVariable, String requestParams, String apiCode){
        this.apiCode = apiCode;
        this.requestParams = requestParams;
        this.requestVariable = requestVariable;
    }
}
