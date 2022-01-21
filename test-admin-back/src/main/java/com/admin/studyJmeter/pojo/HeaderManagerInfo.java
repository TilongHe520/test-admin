package com.admin.studyJmeter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: hetilong
 * @Date: 2022/1/9 11:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeaderManagerInfo {
    public String headerManageName;
    public Map<String,String> headerMap;
}
