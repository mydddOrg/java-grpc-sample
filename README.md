# 基于myddd与gRPC的微服务架构

> 简要说明

- [x] 基于myddd实现
- [x] 使用gRPC做为服务内部通信协议，使用Rest做为对外API通信协议
- [x] 使用Docker Stack容器编排平台部署与运营
- [x] 使用Nginx做为Rest层负载均衡实现，使用容器编排平台DNS能力对gRPC进行负载均衡
- [x] 使用Jenkins进行全自动构建流程
- [x] 使用Fluentd实现分布式日志收集，结合elasticsearch进行日志处理
- [x] 基于open telemetry + jaeger的链路追踪实现
- [x] 使用prometheus实现度量与告警