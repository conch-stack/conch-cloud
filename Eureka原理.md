## Eureka原理

#### 核心类：

- InstanceInfo：注册服务实例
  - instanceId
  - metadata
  - dataCenterInfo
  - leaseInfo：标识该实例的租约信息
  - ......
- LeaseInfo：标识该实例的租约信息
  - renewalIntervalInSecs：Client 续约的间隔周期(心跳周期)
  - durationInSecs：Client 设置的租约有效期时长
  - lastRenewalTimestamp：Server 设置的该租约的最后一次续约时间
  - .....
- ServiceInstance接口：抽象出服务发现的实例信息的公共信息
  - EurekaRegistration implement ServiceInstance,Closeable
  - ......（zookeeper、consul...等）
- InstanceStatus：枚举，标识服务实例的状态
  - UP ：启动状态
  - DOWN：关闭状态
  - STARTING：正在启动
  - OUT_OF_SERVICE：实例停止，无法被路由到，多有用部署
  - UNKNOWN：未知



#### LeaseManager接口：

定义应用服务中心的写操作相关方法：

```java
public interface LeaseManager<T> {
    /* 服务注册 */
    void register(T var1, int var2, boolean var3);
    /* 服务下线 */
    boolean cancel(String var1, String var2, boolean var3);
    /* 服务租约 */
    boolean renew(String var1, String var2, boolean var3);
    /* 服务剔除 */
    void evict();
}
```



#### LookupService接口：

定义了Client从服务中心获取服务实例的查询方法：

```java
public interface LookupService<T> {
  	/* 根据应用名称获取应用信息 */
    Application getApplication(String appName);
    /* 获取所有应用信息 */
    Applications getApplications();
    /* 根据应用ID获取所有服务实例信息 */
    List<InstanceInfo> getInstancesById(String var1);
    /* 获取虚拟主机信息使用Round-robin方式获取下一个服务实例 */
    InstanceInfo getNextServerFromEureka(String virtualHostname, boolean secure);

```



#### ILoadBalancer接口：

定义了路由负载的接口：

```java
public interface ILoadBalancer {
    void addServers(List<Server> var1);
    Server chooseServer(Object var1);
    void markServerDown(Server var1);
    /** @deprecated */
    @Deprecated
    List<Server> getServerList(boolean var1);
    List<Server> getReachableServers();
    List<Server> getAllServers();
}
```

#### IRule接口：

定义了选取Sever的具体逻辑实现接口: 常见的有RoundRobinRule轮询调度算法实现IRule接口

```java
public interface IRule {
    Server choose(Object var1);
    void setLoadBalancer(ILoadBalancer var1);
    ILoadBalancer getLoadBalancer();
}
```



#### PredicateBasedRule抽象类：

可以基于Guava的Predicate进行过滤：

```java
package com.netflix.loadbalancer;

import com.google.common.base.Optional;

public abstract class PredicateBasedRule extends ClientConfigEnabledRoundRobinRule {
    public PredicateBasedRule() {
    }
		// 实现之
    public abstract AbstractServerPredicate getPredicate();

    public Server choose(Object key) {
        ILoadBalancer lb = this.getLoadBalancer();
        Optional<Server> server = this.getPredicate().chooseRoundRobinAfterFiltering(lb.getAllServers(), key);
        return server.isPresent() ? (Server)server.get() : null;
    }
}
```





#### 设计理念：

- AP优于CP
- Peer to Peer：每个副本都可以接受写请求（对比主从复制架构）
- Self Preservation：自我保护机制-当网络发送抖动或短暂不可用，存在心跳误判：
  - 计算每分钟心跳期望值？？？



#### Chaos Engineering：混沌工程

通过一系列可控的实现模拟真实世界可能出现的故障，来暴露系统缺陷，驱动工程师去构建更具弹性的服务。

- 随机关闭依赖服务
- 模拟增加依赖服务的延时
- 模拟机器故障
- 网络中断



#### 实战：

- 在线扩容Eureka-Server
  - config+eureka
  - config-server的加密功能？？？
- 多Zone的Eureka-Server
  - zone+zuul
  - 区域感知
- 支持Remote Region
  - 先分region，下面再分zone
  - 跨region的fallback
    - 当访问某个region失败，将会自动fallback到remote-region的相应实例上
- Https支持
- Eureka Admin
- metadata
  - 灰度控制 + 不宕机升级
  - 自定义MetadataAwarePredicate？？？



1. 在线扩容Eureka-Server

config-server + eureka-server + eureka-client

##### 启动config-server

```
mvn spring-boot:run
```



##### 按profile启动eureka server

##### peer1

```
mvn spring-boot:run -Dspring.profiles.active=peer1
```

按这个profile启动时，会出现如下的error日志

```
com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
java.net.ConnectException
```

> 这是因为这里为了后续演示扩容方便，peer1的fetchRegistry及fetchRegistry的属性设置为true的缘故，如果是standalone的eureka server，不想看到该报错，可以自己设置false

##### peer2 & peer3

```
mvn spring-boot:run -Dspring.profiles.active=peer2
mvn spring-boot:run -Dspring.profiles.active=peer3
```



先启动新节点，再修改配置中心配置文件，动态更新配置（可依赖git做）







