# Beihu-Cloud
##### Spring Cloud Learning

- 拆分服务
- 自动化部署
- 弹性、容错
- 扩展
- 监控+日志
- 去中心化管理数据：每个服务管理自己的数据库

##### 问题：

- 运维编排能力
- 一致性
- 分布式：网络延迟、事务、异步消息



##### 微服务：

- 服务治理
  - 注册、发现
  - 服务路由
    - 服务上下线
    - 在线测试
    - 机房就近选择
    - A/B测试
    - 灰度发布
  - 负载均衡
    - 目标状态负载
    - 目标权重负载
  - 自我保护
    - 服务降级
    - 优雅降级
    - 流量控制
  - Feature、Consul、Zookeeper、etcd、Eureka、Nacos
- 配置中心
  - config、Apollo
- 全链路监控
  - 定位慢调用：慢Web服务、慢RPC服务、慢SQL
  - 定位各种错误：4xx、5xx、server error
  - 定位各种异常：Error Exception、Fatal Exception
  - 展现依赖和拓扑：域拓扑、服务拓扑、Trace拓扑
  - Trace调用链：展示端到端的调用、这次调用的上下文信息、异常日志、每个调用点的耗时
  - 应用告警：根据运维设定的告警规则，扫描指标数据，上报告警信息至告警平台
  - 京东Hydra(未来源)、阿里Eagleye(未来源)、Skywalking（Zipkin+Pinpoint+CAT）非侵入式埋点、Sleuth
- 分布式事务
- 分布式定时任务
- 消息中间件
- API网关
  - 统一接入功能：高性能、高并发、高可用：负载均衡、容灾切换、异地多活
  - 协议适配功能：Http->RPC、REST等
  - 流量管控功能：流量管控、流量调拨；熔断+服务降级；异地多活：请求流量分片、路由到不同机房
  - 安全防护功能：请求安全过滤；IP黑名单；URL黑名单；分控防刷；防恶意攻击
  - Zuul（一代）、Gateway（二代）
  - Gateway：基于Netty异步提高吞吐；安全+监控/埋点+限流
- 分布式缓存
- 数据库中间件
- 领域驱动
  - 业务架构治理、代码防腐
  - Halo
- RPC
  - GRPC整合
- Dubbo整合
  - spring-cloud-dubbo