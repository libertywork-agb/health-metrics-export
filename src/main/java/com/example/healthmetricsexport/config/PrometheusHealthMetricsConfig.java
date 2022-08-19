package com.example.healthmetricsexport.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PrometheusHealthMetricsConfig {

    public PrometheusHealthMetricsConfig(final MeterRegistry registry, final HealthEndpoint healthEndpoint) {
        registerGauge("health_overall", healthEndpoint.health(), registry);
        registerGauge("health_disk_space", healthEndpoint.healthForPath("diskSpace"), registry);
        registerGauge("health_ping", healthEndpoint.healthForPath("ping"), registry);
    }

    private void registerGauge(final String name, final HealthComponent healthComponent, final MeterRegistry registry) {
        Gauge.builder(name, healthComponent, this::getStatusCode).strongReference(true).register(registry);
    }

    private int getStatusCode(final HealthComponent healthComponent) {
        final Status status = healthComponent.getStatus();

        return Status.UP.equals(status) ? 1
                : Status.DOWN.equals(status) ? 0
                : Status.OUT_OF_SERVICE.equals(status) ? -1
                : -2;
    }

}
