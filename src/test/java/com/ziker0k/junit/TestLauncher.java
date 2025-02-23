package com.ziker0k.junit;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {
    public static void main(String[] args) {
        var launcher = LauncherFactory.create();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        LauncherDiscoveryRequest discoveryRequest = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectPackage("com.ziker0k.junit.service"))
                .filters(TagFilter.includeTags("login"))
                .build();

        launcher.execute(discoveryRequest, listener);

        try(var printWriter = new PrintWriter(System.out)) {
            listener.getSummary().printTo(printWriter);
        }
    }
}
