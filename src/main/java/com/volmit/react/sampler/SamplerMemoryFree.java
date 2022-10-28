package com.volmit.react.sampler;

import com.volmit.react.api.sampler.ReactCachedSampler;
import com.volmit.react.util.Form;

public class SamplerMemoryFree extends ReactCachedSampler {
    public static final String ID = "memory-free";
    private final Runtime runtime;

    public SamplerMemoryFree() {
        super(ID, 50);
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public double onSample() {
        return runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory());
    }

    @Override
    public String formattedValue(double t) {
        return Form.memSizeSplit((long) t, 1)[0];
    }

    @Override
    public String formattedSuffix(double t) {
        return Form.memSizeSplit((long) t, 1)[1];
    }
}
