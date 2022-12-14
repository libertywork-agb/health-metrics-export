This is a reproduction of a bug that occurs when mapping overall health to a Prometheus metric in a Spring Boot application deployed in a Kubernetes environment. The overall health reports UP on the Actuator health page, but the overall health metric reports 0.0 (DOWN) on the Actuator Prometheus page.

This only occurs when health probes (liveness and readiness) are enabled. When enabled, the `SystemHealth` status always reports DOWN.

### Resolution
The problem was that I was passing `HealthComponent` into the `Gauge` builder instead of `HealthEndpoint`, which is what the Spring Boot documentation does. Based on the observed issue, the `HealthComponent` must go stale and not get updated.
