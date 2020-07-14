package ir.ac.sbu.data_mining.controller;

import ir.ac.sbu.data_mining.annotation.PrometheusMetric;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsController {
    @PrometheusMetric(metricName = "crawler_exporter_request_count")
    private AtomicInteger requestCount = new AtomicInteger(0);
    @PrometheusMetric(metricName = "crawler_exporter_valid_request_count")
    private AtomicInteger validRequestCount = new AtomicInteger(0);

    public String process(Object... objects) throws IllegalAccessException {
        validRequestCount.incrementAndGet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                if (declaredField.isAnnotationPresent(PrometheusMetric.class)) {
                    PrometheusMetric annotation = declaredField.getAnnotation(PrometheusMetric.class);
                    String metricName = annotation.metricName();
                    stringBuilder.append(metricName).append(" ").append(declaredField.get(object)).append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    public void increaseRequestCount() {
        requestCount.incrementAndGet();
    }
}
