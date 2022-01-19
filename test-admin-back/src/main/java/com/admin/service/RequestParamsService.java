package com.admin.service;

import com.admin.mapper.RequestParamsMapper;
import com.admin.pojo.RequestParamsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestParamsService {

    @Autowired
    RequestParamsMapper requestParamsMapper;

    public int batchInsert(List<RequestParamsInfo> infoList) {
        return requestParamsMapper.insertRequestParamsInfo(infoList);
    }

}
