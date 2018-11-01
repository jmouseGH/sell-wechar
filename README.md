微信点餐系统
===

功能介绍:
---
1. 在微信公众号中, 有个微信点餐, 或者点网址可以进入点餐界面
2. 点支付后,

Pc端
1. 扫描登录
2. 卖家管理系统
3. 商品管理

消息互通:
1. 买家下单后, PC专家端有通知 ( web socket)
2. 卖家端点结束订单,买家端有微信通知.

技术:
---
前端: vue, webapp
后端: springboot , bootstrap+freemarker+jquery
前后端通过 restful相连

其中 还有数据库相关技术, 缓存技术, 消息推送技术
1. springBoot+jpa
2. springboot+mybatis
3. spirngboot+ redis
4. 分布式session+ 分布式锁
5. websocket

微信相关: 扫码登录, 消息推送

软件版本:
---
springboot 1.5.2
linux centos 7.3
idea 2017.1.1
jdk 1.8.0_111
maven 3.3.9
mysql 5.7.17
nginx 1.11.7
redis 3.2.8





