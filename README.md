This is a reproduction of a bug that occurs when mapping overall health to a Prometheus metric in a Spring Boot application deployed in a Kubernetes environment. The overall health reports UP on the Actuator health page, but the overall health metric reports 0.0 (DOWN) on the Actuator Prometheus page.

This only occurs when health probes (liveness and readiness) are enabled. When enabled, the `SystemHealth` status always reports DOWN.
