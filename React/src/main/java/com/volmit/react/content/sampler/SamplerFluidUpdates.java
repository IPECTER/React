package com.volmit.react.content.sampler;

import com.volmit.react.React;
import com.volmit.react.api.sampler.ReactCachedRateSampler;
import com.volmit.react.api.sampler.ReactCachedSampler;
import com.volmit.react.util.format.Form;
import com.volmit.react.util.math.M;
import com.volmit.react.util.math.RollingSequence;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class SamplerFluidUpdates extends ReactCachedRateSampler implements Listener {
    public static final String ID = "fluid";

    public SamplerFluidUpdates() {
        super(ID, 1000);
    }

    @Override
    public Material getIcon() {
        return Material.MILK_BUCKET;
    }

    @Override
    public void start() {
        super.start();
        React.instance.registerListener(this);
    }

    @Override
    public void stop() {
        super.stop();
        React.instance.unregisterListener(this);
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        BlockData data = event.getBlock().getBlockData();

        if (data instanceof Levelled l) {
            increment();
            getChunkCounter(event.getBlock()).addAndGet(1D);
        }
    }

    @Override
    public String formattedValue(double t) {
        return Form.f(Math.round(t));
    }

    @Override
    public String formattedSuffix(double t) {
        return "FLOW/s";
    }
}