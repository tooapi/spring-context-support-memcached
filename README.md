# spring-context-support-memcached





提供了对Spring 原生 缓存注解的支持 不改变原有程序结构

支持集群配置

使用xmemcached-client集成

 如下实例  只需要添加注解即可
 
@Cacheable(value = "UserService", key = "#root.methodName+#channelCode+#bankServCode+#pattern")



