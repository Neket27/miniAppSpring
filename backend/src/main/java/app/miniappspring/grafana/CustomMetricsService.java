//package app.miniappspring.grafana;
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class CustomMetricsService implements BeanPostProcessor {
//
//    private final Counter customMetricCounter;
//
//    public CustomMetricsService(MeterRegistry meterRegistry) {
//        customMetricCounter = Counter.builder("custom_metric_name")
//                .description("Description of custom metric")
//                .tags("environment", "development")
//                .register(meterRegistry);
//    }
//
//    public void incrementCustomMetric() {
//        customMetricCounter.increment();
//    }
//}