package com.admin.studyJmeter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hetilong
 * @Date: 2022/1/9 12:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadGroupInfo {
    public String threadName;
    public int threadNumber;

    public int rampUp;
}
