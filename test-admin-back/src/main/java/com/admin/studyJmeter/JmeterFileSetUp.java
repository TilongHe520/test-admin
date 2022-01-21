package com.admin.studyJmeter;

import com.admin.studyJmeter.pojo.*;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.CSVDataSet;
import org.apache.jmeter.config.CSVDataSetBeanInfo;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.extractor.RegexExtractor;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.gui.HeaderPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.property.*;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.timers.ConstantThroughputTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: hetilong
 * @Date: 2022/1/9 11:10
 */
public class JmeterFileSetUp {

    /**
     * 获取csv配置
     * @return
     */
    public CSVDataSet getCSVDataSet(CsvDataSetInfo csvDataSetInfo){
        CSVDataSet csvDataSet = new CSVDataSet();
        csvDataSet.setEnabled(true);
        //设置csv名称
        csvDataSet.setName(csvDataSetInfo.getCsvName());
        csvDataSet.setProperty(TestElement.TEST_CLASS, CSVDataSet.class.getName());
        csvDataSet.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());

        csvDataSet.setProperty(new StringProperty("filename", csvDataSetInfo.getCsvPath()));
        csvDataSet.setProperty(new StringProperty("fileEncoding", csvDataSetInfo.getEncoding()));
        csvDataSet.setProperty(new BooleanProperty("ignoreFirstLine", false));
        csvDataSet.setProperty(new BooleanProperty("quotedData", true));
        csvDataSet.setProperty(new BooleanProperty("recycle", false));
        csvDataSet.setProperty(new BooleanProperty("stopThread", false));
        csvDataSet.setProperty(new StringProperty("variableNames", csvDataSetInfo.getVariableNames()));
        csvDataSet.setProperty(new StringProperty("shareMode", CSVDataSetBeanInfo.getShareTags()[0]));
        csvDataSet.setProperty(new StringProperty("delimiter", csvDataSetInfo.getDelimiter()));

        return csvDataSet;
    }

    /**
     * 设置请求头信息
     * @return
     */
    public HeaderManager getHeaderManager(HeaderManagerInfo headerManagerInfo) {
        ArrayList<TestElementProperty> headerMangerList = new ArrayList<>();
        HeaderManager headerManager = new HeaderManager();

        Set<String> keySet = headerManagerInfo.getHeaderMap().keySet();
        for(String k:keySet){
            Header header = new Header(k, headerManagerInfo.getHeaderMap().get(k));
            TestElementProperty HeaderElement = new TestElementProperty("", header);
            headerMangerList.add(HeaderElement);
        }
        headerManager.setEnabled(true);
        headerManager.setName(headerManagerInfo.getHeaderManageName());
        headerManager.setProperty(new CollectionProperty(HeaderManager.HEADERS, headerMangerList));
        headerManager.setProperty(new StringProperty(TestElement.TEST_CLASS, HeaderManager.class.getName()));
        headerManager.setProperty(new StringProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName()));
        return headerManager;
    }

    /**
     * 设置测试计划基本信息
     * @return
     */
    public TestPlan getTestPlan(TestPlanInfo testPlanInfo) {
        TestPlan testPlan = new TestPlan(testPlanInfo.getTestPlanName());
        testPlan.setFunctionalMode(false);
        testPlan.setSerialized(false);
        testPlan.setTearDownOnShutdown(true);
        testPlan.setComment(testPlanInfo.getTestPlanComment());
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setProperty(new BooleanProperty(TestElement.ENABLED, true));
        testPlan.setProperty(new StringProperty(TestElement.COMMENTS, ""));
        testPlan.setTestPlanClasspath("");
        Arguments arguments = new Arguments();
        testPlan.setUserDefinedVariables(arguments);
        return testPlan;
    }

    /**
     * 设置循环控制器的基本信息
     * @return
     */
    public LoopController getLoopController() {
        LoopController loopController = new LoopController();
        loopController.setContinueForever(false);
        loopController.setProperty(new StringProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName()));
        loopController.setProperty(new StringProperty(TestElement.TEST_CLASS, LoopController.class.getName()));
        loopController.setProperty(new StringProperty(TestElement.NAME, "循环控制器"));
        loopController.setProperty(new StringProperty(TestElement.ENABLED, "true"));
        loopController.setProperty(new StringProperty(LoopController.LOOPS, "1"));
        return loopController;
    }

    /***
     * 创建线程组
     * @param loopController 循环控制器
     * @return
     */
    public ThreadGroup getThreadGroup(LoopController loopController, ThreadGroupInfo t) {
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(t.getThreadNumber());
        threadGroup.setRampUp(t.getRampUp());
        threadGroup.setDelay(0);
        threadGroup.setDuration(0);
        threadGroup.setProperty(new StringProperty(ThreadGroup.ON_SAMPLE_ERROR, "continue"));
        threadGroup.setScheduler(false);
        threadGroup.setName(t.getThreadName());
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup.setProperty(new BooleanProperty(TestElement.ENABLED, true));
        threadGroup.setProperty(new TestElementProperty(ThreadGroup.MAIN_CONTROLLER, loopController));
        return threadGroup;
    }

    /***
     * 创建http请求信息
     * @return
     */
    public HTTPSamplerProxy getHttpSamplerProxy(HttpRequestInfo httpRequestInfo) {
        HTTPSamplerProxy httpSamplerProxy = new HTTPSamplerProxy();
        Arguments httpsSamplerArguments = new Arguments();
        HTTPArgument httpArgument = new HTTPArgument();
        httpArgument.setProperty(new BooleanProperty("HTTPArgument.always_encode", false));
        httpArgument.setProperty(new StringProperty("Argument.value", httpRequestInfo.getBody()));
        httpArgument.setProperty(new StringProperty("Argument.metadata", "="));
        ArrayList<TestElementProperty> list1 = new ArrayList<>();
        list1.add(new TestElementProperty("", httpArgument));
        httpsSamplerArguments.setProperty(new CollectionProperty("Arguments.arguments", list1));
        httpSamplerProxy.setProperty(new TestElementProperty("HTTPsampler.Arguments", httpsSamplerArguments));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.domain", httpRequestInfo.getUrl()));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.port", httpRequestInfo.getPort()));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.protocol", httpRequestInfo.getProtocol()));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.path", httpRequestInfo.getApi()));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.method", httpRequestInfo.getMethod()));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.contentEncoding", httpRequestInfo.getContentEncoding()));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.follow_redirects", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.postBodyRaw", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.auto_redirects", false));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.use_keepalive", true));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.DO_MULTIPART_POST", false));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.gui_class", "org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui"));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.test_class", "org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy"));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.name", httpRequestInfo.getHttpName()));
        httpSamplerProxy.setProperty(new StringProperty("TestElement.enabled", "true"));
        httpSamplerProxy.setProperty(new BooleanProperty("HTTPSampler.postBodyRaw", true));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.embedded_url_re", ""));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.connect_timeout", ""));
        httpSamplerProxy.setProperty(new StringProperty("HTTPSampler.response_timeout", ""));
        return httpSamplerProxy;
    }

    public RegexExtractor getRegexExtractor(RegexExtractorInfo regexExtractorInfo){
        RegexExtractor regexExtractor = new RegexExtractor();

        regexExtractor.setProperty(new StringProperty("TestElement.gui_class","RegexExtractorGui"));
        regexExtractor.setProperty(new StringProperty("TestElement.test_class","RegexExtractor"));
        regexExtractor.setProperty(new StringProperty("TestElement.enabled","true"));
        regexExtractor.setProperty(new StringProperty("TestElement.name",regexExtractorInfo.getReName()));

        regexExtractor.setProperty(new StringProperty("RegexExtractor.useHeaders","false"));
        regexExtractor.setProperty(new StringProperty("RegexExtractor.refname",regexExtractorInfo.getRefName()));
        regexExtractor.setProperty(new StringProperty("RegexExtractor.regex",regexExtractorInfo.getRegex()));
        regexExtractor.setProperty(new StringProperty("RegexExtractor.template",regexExtractorInfo.getTemplate()));
        regexExtractor.setProperty(new StringProperty("RegexExtractor.default",regexExtractorInfo.getDefaultValue()));
        regexExtractor.setProperty(new StringProperty("RegexExtractor.match_number",regexExtractorInfo.getMatch_number()));

        return regexExtractor;
    }

    /***
     * 监听结果
     * @param replayLogPath  将结果保存到文件中，这个是文件的路径
     * @return
     */
    public List<ResultCollector> getResultCollector(String replayLogPath) {
        // 察看结果树
        List<ResultCollector> resultCollectors = new ArrayList<>();
        Summariser summariser = new Summariser("速度");
        ResultCollector resultCollector = new ResultCollector(summariser);
        resultCollector.setProperty(new BooleanProperty("ResultCollector.error_logging", false));
        resultCollector.setProperty(new ObjectProperty("saveConfig", getSampleSaveConfig()));
        resultCollector.setProperty(new StringProperty("TestElement.gui_class", "org.apache.jmeter.visualizers.ViewResultsFullVisualizer"));
        resultCollector.setProperty(new StringProperty("TestElement.name", "察看结果树"));
        resultCollector.setProperty(new StringProperty("TestElement.enabled", "true"));
        resultCollector.setProperty(new StringProperty("filename", replayLogPath));
        resultCollectors.add(resultCollector);

        // 结果汇总
        ResultCollector resultTotalCollector = new ResultCollector();
        resultTotalCollector.setProperty(new BooleanProperty("ResultCollector.error_logging", false));
        resultTotalCollector.setProperty(new ObjectProperty("saveConfig", getSampleSaveConfig()));
        resultTotalCollector.setProperty(new StringProperty("TestElement.gui_class", "org.apache.jmeter.visualizers.SummaryReport"));
        resultTotalCollector.setProperty(new StringProperty("TestElement.name", "汇总报告"));
        resultTotalCollector.setProperty(new StringProperty("TestElement.enabled", "true"));
        resultTotalCollector.setProperty(new StringProperty("filename", ""));
        resultCollectors.add(resultTotalCollector);

        return resultCollectors;
    }

    private static SampleSaveConfiguration getSampleSaveConfig() {
        SampleSaveConfiguration sampleSaveConfiguration = new SampleSaveConfiguration();
        sampleSaveConfiguration.setTime(true);
        sampleSaveConfiguration.setLatency(true);
        sampleSaveConfiguration.setTimestamp(true);
        sampleSaveConfiguration.setSuccess(true);
        sampleSaveConfiguration.setLabel(true);
        sampleSaveConfiguration.setCode(true);
        sampleSaveConfiguration.setMessage(true);
        sampleSaveConfiguration.setThreadName(true);
        sampleSaveConfiguration.setDataType(true);
        sampleSaveConfiguration.setEncoding(false);
        sampleSaveConfiguration.setAssertions(true);
        sampleSaveConfiguration.setSubresults(true);
        sampleSaveConfiguration.setResponseData(false);
        sampleSaveConfiguration.setSamplerData(false);
        sampleSaveConfiguration.setAsXml(false);
        sampleSaveConfiguration.setFieldNames(true);
        sampleSaveConfiguration.setResponseHeaders(false);
        sampleSaveConfiguration.setRequestHeaders(false);
        //sampleSaveConfiguration.setAssertionResultsFailureMessage(true);  responseDataOnError
        sampleSaveConfiguration.setAssertionResultsFailureMessage(true);
        //sampleSaveConfiguration.setsserAtionsResultsToSave(0); assertionsResultsToSave
        sampleSaveConfiguration.setBytes(true);
        sampleSaveConfiguration.setSentBytes(true);
        sampleSaveConfiguration.setUrl(true);
        sampleSaveConfiguration.setThreadCounts(true);
        sampleSaveConfiguration.setIdleTime(true);
        sampleSaveConfiguration.setConnectTime(true);
        return sampleSaveConfiguration;
    }

    /***
     * 限制QPS设置
     * @param throughputTimer
     * @return
     */
    public ConstantThroughputTimer getConstantThroughputTimer(int throughputTimer) {
        ConstantThroughputTimer constantThroughputTimer = new ConstantThroughputTimer();
        constantThroughputTimer.setEnabled(true);
        constantThroughputTimer.setName("常数吞吐量定时器");
        constantThroughputTimer.setProperty(TestElement.TEST_CLASS, ConstantThroughputTimer.class.getName());
        constantThroughputTimer.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());
        constantThroughputTimer.setCalcMode(ConstantThroughputTimer.Mode.AllActiveThreads.ordinal());

        constantThroughputTimer.setProperty(new IntegerProperty("calcMode", ConstantThroughputTimer.Mode.AllActiveThreads.ordinal()));
        DoubleProperty doubleProperty = new DoubleProperty();
        doubleProperty.setName("throughput");
        doubleProperty.setValue(throughputTimer * 60f);
        constantThroughputTimer.setProperty(doubleProperty);
        return constantThroughputTimer;
    }
}
