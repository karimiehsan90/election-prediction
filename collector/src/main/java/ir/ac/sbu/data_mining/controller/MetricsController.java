package ir.ac.sbu.data_mining.controller;

import ir.ac.sbu.data_mining.annotations.PrometheusMetric;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsController {
    public String process(Object... objects) throws IllegalAccessException {
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
}
