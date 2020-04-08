package com.gedk.cloud.gateway.predicate;

import java.time.LocalTime;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/4 15:25
 */
public class GedkTimeBetweenConfig {
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
