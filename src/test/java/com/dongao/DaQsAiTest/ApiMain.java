package com.dongao.DaQsAiTest;

import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class ApiMain {
    public static void main(String[] args){
        //junit-platform-launcher这个jar包，必须1.6.0才能正确运行
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        DiscoverySelectors.selectPackage("DaQsAiTest"),
                        DiscoverySelectors.selectClass(ApiDDTest.class)
                ).filters(
                        ClassNameFilter.includeClassNamePatterns(".*Test")
                )
                .build();
        System.out.println(request);

        Launcher launcher = LauncherFactory.create();

        TestPlan testPlan = launcher.discover(request);
        testPlan.getRoots().forEach(uid->{
            System.out.println(uid.toString());
        });

// Register a listener of your choice
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        System.out.println(summary.getTestsSucceededCount());
        System.out.println(summary.getTestsFoundCount());
// Do something with the TestExecutionSummary.
    }
}
