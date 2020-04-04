## Ribbon原理

客户端负载均衡

### 策略：

| 策略                      | 名称             | 描述                                                         |
| ------------------------- | ---------------- | ------------------------------------------------------------ |
| RandomRule                | 随机策略         | 随机选择Server                                               |
| RoundRobinRule            | 轮询策略         | 按顺序轮询选择Server                                         |
| RetryRule                 | 重试策略         | 在给定时间段内选择Sever不成功，则重试                        |
| BestAvailableRule         | 最低并发策略     | 遍历Server，忽略打开断路器的，选择一个并发连接最低Server     |
| AvailabilityFilteringRule | 可用过滤策略     | 过滤掉一直连接失败并标记为circuit tripped的Server<br />过滤掉那些高并发的Server（active connections超过配置的阀值时） |
| ResponseTimeWeightedRule  | 响应时间加权策略 | 响应时间越长，权重越低，被选中的概率越低                     |
| ZoneAvoidanceRule         | 区域权衡策略     | 综合判断Sever所在区域的性能和Server的可用性轮询选择Server    |



### 原理：

基于RestTemplate

拦截HTTP请求，选择Server，进行负载策略选择，最终触发调用