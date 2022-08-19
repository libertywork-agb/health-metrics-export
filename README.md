This is a reproduction of a bug that occurs when mapping overall health to a Prometheus metric in a Spring Boot project. The overall health reports UP on
the Actuator health page, but the overall health metric reports 0.0 (DOWN) on the Actuator Prometheus page.
