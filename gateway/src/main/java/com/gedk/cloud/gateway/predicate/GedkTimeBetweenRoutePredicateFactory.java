package com.gedk.cloud.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/4/4 15:20
 */
@Component
public class GedkTimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<GedkTimeBetweenConfig> {
    public GedkTimeBetweenRoutePredicateFactory() {
        super(GedkTimeBetweenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(GedkTimeBetweenConfig config) {
        LocalTime startTime = LocalTime.parse(config.getStartTime());
        LocalTime endTime = LocalTime.parse(config.getEndTime());
        return serverWebExchange -> {
            LocalTime now = LocalTime.now();
            return now.isAfter(startTime) && now.isBefore(endTime);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("startTime","endTime");
    }
}
