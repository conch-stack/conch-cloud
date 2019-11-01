## Eureka原理

##### 核心类：

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



##### LeaseManager接口：

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



##### LookupService接口：

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



##### 设计理念：

