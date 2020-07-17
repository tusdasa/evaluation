# README

## 毕设：微服务教学评估系统

> 编译

> Maven

```
$ mvn -version
 Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
```

> Java

```
$ java -version
  java version "1.8.0_241"
  Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
  Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
```
> build

```
package.bat
```
[或](https://mirrors.huaweicloud.com/mysql/Downloads/MySQLInstaller/mysql-installer-community-5.7.30.0.msi)

```
mvn package -Dmaven.test.skip=true
```
```
> set BUILD_DIR=E:\Project\evaluation\build
> release.bat
```
可在BUILD_DIR环境变量所在的目录得到当前日期的文件夹，包含了所有的jar包
> 运行环境

| 软件名                                                       |          版本          |
| :----------------------------------------------------------- | :--------------------: |
| [MongoDB](https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2012plus-4.2.6-signed.msi) |          4.2           |
| [MySQL](https://mirrors.huaweicloud.com/mysql/Downloads/MySQLInstaller/mysql-installer-community-5.7.30.0.msi) |          5.7           |
| [Redis](https://github.com/microsoftarchive/redis/releases/download/win-3.2.100/Redis-x64-3.2.100.msi) |          3.2           |
| [Elasticsearch](https://mirrors.huaweicloud.com/elasticsearch/6.8.6/elasticsearch-6.8.6.zip) |         6.8.6          |
| [RabbitMQ](https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.3/rabbitmq-server-3.8.3.exe) | 3.8.3(Erlang otp 22.3) |
| [MyCat](http://dl.mycat.org.cn/1.6.7.5/2020-4-10/Mycat-server-1.6.7.5-release-20200410174409-win.tar.gz) |        1.6.7.5         |
| [Openresty](https://openresty.org/download/openresty-1.15.8.3-win64.zip) |        1.15.8.3        |
| [Consul](https://www.consul.io/)                             |         1.8.0          |
| [CoreDNS](https://coredns.io/)                               |         1.7.0          |

DNS记录

```192.168.99.104 mycat.tusdasa.net
127.0.0.1 mongodb.tusdasa.net
127.0.0.1 consul.tusdasa.net
127.0.0.1 redis.tusdasa.net
127.0.0.1 elasticsearch.tusdasa.net
127.0.0.1 rabbitmq.tusdasa.net
127.0.0.1 mail.local.net
127.0.0.1 api.tusdasa.net```
```




## 文档 
TODO
