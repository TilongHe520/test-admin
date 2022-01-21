package com.admin.studyJmeter.pojo;

import lombok.Data;

/**
 * @Author: hetilong
 * @Date: 2022/1/9 11:19
 */
@Data
public class CsvDataSetInfo {
    /**
     * csv控制器名称
     */
    public String csvName;
    /**
     * csv文件路径
     */
    public String csvPath;
    /**
     * 编码
     * 一般为 utf-8
     */
    public String encoding;
    /**
     * 变量名称
     */
    public String variableNames;
    /**
     * 多个变量一般以 , 分隔
     */
    public String delimiter;
}
