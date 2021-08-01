package com.admin.mapper;

import com.admin.pojo.RequestParamsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestParamsMapper {

    int insertRequestParamsInfo(List<RequestParamsInfo> infoList);
}
