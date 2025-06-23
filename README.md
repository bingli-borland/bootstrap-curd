# bootstrap-curd
基于SpringBoot，整合MyBatis, Hibernate, JPA, Druid, bootstrap, thymeleaf等，进行增删改查操作、文件上传、文件下载、excel生成的Demo


- 默认开启8080端口，项目基础路径为/curd，可在application.properties中更改

- 运行后，访问`http://主机ip:端口号/curd/`即跳转到注册登录页面

![employee](https://img-blog.csdnimg.cn/20200130082937376.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9oYW5xdWFuLmJsb2cuY3Nkbi5uZXQ=,size_16,color_FFFFFF,t_70)

curl -X POST "http://localhost:8087/jpademo/user" --data-raw 'id=1&name=admin&password=B#2008_2108#es'

curl http://localhost:8087/jpademo/user?id=1

curl -X POST "http://localhost:8087/jpademo/user/update;jsessionid=0000HtrK3unMUKxwBi8ZkEVYVpB:f89a24ac-ef77-4fcf-a1bd-70990d09bc05"  --data-raw 'id=1&name=admin&password=B#2008_2108#es1'
