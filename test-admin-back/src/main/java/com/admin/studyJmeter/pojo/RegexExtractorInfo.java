package com.admin.studyJmeter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hetilong
 * @Date: 2022/1/10 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegexExtractorInfo {
    public String reName;
    public String refName;
    public String regex;
    public String template;
    public String defaultValue;
    public String match_number;
}
