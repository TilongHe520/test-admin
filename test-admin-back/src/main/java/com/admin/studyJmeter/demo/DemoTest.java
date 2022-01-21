package com.admin.studyJmeter.demo;


import com.admin.studyJmeter.JmeterFileSetUp;
import com.admin.studyJmeter.pojo.*;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.extractor.RegexExtractor;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hetilong
 * @Date: 2022/1/10 17:12
 */
public class DemoTest {
    public static final String replayLogPath = "/Users/maoyan/Desktop/workcase/replay_result.log";
    public static final String jmxPath = "/Users/maoyan/Desktop/workcase/test9.jmx";

    public static void main(String[] args) throws IOException {

        String jemterHome = "/Users/maoyan/develop/apache-jmeter-5.4.1";
        JMeterUtils.setJMeterHome(jemterHome);
        JMeterUtils.loadJMeterProperties(JMeterUtils.getJMeterBinDir() + "/jmeter.properties");

        JmeterFileSetUp jmeterFileSetUp = new JmeterFileSetUp();

        TestPlanInfo testPlanInfo = new TestPlanInfo("jmeter test plan","java录制jmeter脚本");

        ThreadGroupInfo threadGroupInfo = new ThreadGroupInfo("test ThreadGroup",4,1);

        Map<String,String> headerMap = new HashMap<>();
        //headerMap.put("cookies","wowefbewruvbwbvsdabfuiwer");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Accept","application/json");
        HeaderManagerInfo headerManagerInfo = new HeaderManagerInfo("test headerManager",headerMap);

        String body = "{\"bios\":\"FDplnHysqmuQFeh5vSj5lSV2Ihp5xjNn\"}";
        HttpRequestInfo httpRequestInfo = new HttpRequestInfo("hkpos.dev.maoyan.team",
                "api/user/login/terminal/query?locale=zh_CN",
                "",
                "POST",
                "http",
                "utf-8",
                "terminalInfo",
                body);

        RegexExtractorInfo regexExtractorInfo = new RegexExtractorInfo("eventId",
                "terminalId",
                "\"id\":(.*?),",
                "$1$",
                "1971",
                "0");


        TestPlan testPlan = jmeterFileSetUp.getTestPlan(testPlanInfo);
        LoopController loopController = jmeterFileSetUp.getLoopController();
        ThreadGroup threadGroup =jmeterFileSetUp.getThreadGroup(loopController,threadGroupInfo);
        HTTPSamplerProxy httpSamplerProxy = jmeterFileSetUp.getHttpSamplerProxy(httpRequestInfo);
        List<ResultCollector> resultCollector = jmeterFileSetUp.getResultCollector(replayLogPath);
        // 获取请求头信息
        HeaderManager headerManager = jmeterFileSetUp.getHeaderManager(headerManagerInfo);
        RegexExtractor regexExtractor = jmeterFileSetUp.getRegexExtractor(regexExtractorInfo);

        HashTree fourHashTree = new HashTree();
        //resultCollector.stream().forEach(item->fourHashTree.add(item));
        fourHashTree.add(regexExtractor);

        HashTree thirdHashTree = new HashTree();
        thirdHashTree.add(headerManager);
        resultCollector.stream().forEach(item->thirdHashTree.add(item));
        thirdHashTree.add(httpSamplerProxy,fourHashTree);

        HashTree secondHashTree = new HashTree();
        secondHashTree.add(threadGroup,thirdHashTree);

        HashTree firstHashTree = new HashTree();
        firstHashTree.add(testPlan,secondHashTree);

        try {
            SaveService.saveTree(firstHashTree, new FileOutputStream(jmxPath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
        StandardJMeterEngine jMeterEngine = new StandardJMeterEngine();
        jMeterEngine.configure(firstHashTree);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jMeterEngine.run();
         **/

        String command = JMeterUtils.getJMeterBinDir() + "/jmeter -n -t " + jmxPath + " -l /Users/maoyan/Desktop/workcase/replay_result1.jtl";
        Runtime.getRuntime().exec(command);
        System.out.println("运行成功!!!");

    }
}
