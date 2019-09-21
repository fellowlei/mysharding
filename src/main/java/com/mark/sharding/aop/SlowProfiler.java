package com.mark.sharding.aop;

import java.util.ArrayList;
import java.util.List;


public class SlowProfiler {
    public static final ThreadLocal<List<String>> slowMethodTrace = new ThreadLocal<List<String>>(){
        @Override
        protected List<String> initialValue() {
            return new ArrayList<>();
        }
    };

    public static void addSlowMethodTrace(String trace) {
        slowMethodTrace.get().add(trace);
    }

    public static String getSlowMethodTrace() {
        List<String> traces = slowMethodTrace.get();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < traces.size(); i++) {
            sb.append(traces.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static void clearTrace(){
        slowMethodTrace.remove();
    }
}
