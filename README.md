# bootstrap-curd
基于SpringBoot，整合MyBatis, Hibernate, JPA, Druid, bootstrap, thymeleaf等，进行增删改查操作、文件上传、文件下载、excel生成的Demo


- 默认开启8080端口，项目基础路径为/curd，可在application.properties中更改

- 运行后，访问`http://主机ip:端口号/curd/`即跳转到注册登录页面

![employee](https://img-blog.csdnimg.cn/20200130082937376.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9oYW5xdWFuLmJsb2cuY3Nkbi5uZXQ=,size_16,color_FFFFFF,t_70)

curl -X POST "http://localhost:8080/jpademo/user" --data-raw 'id=1&name=admin&password=B#2008_2108#es'

curl http://localhost:8080/jpademo/user?id=1

## jpa
### 增加事务注解并加参数，回滚成功
curl -X POST "http://localhost:8080/jpademo/user/update"  --data-raw 'id=1&name=admin&password=B#2008_2108#es1'

### 不加事务注解，回滚失败
curl -X POST "http://localhost:8080/jpademo/user/update2" --data-raw 'id=1&name=admin&password=B#2008_2108#es2'

### 增加事务注解不加参数，回滚成功
curl -X POST "http://localhost:8080/jpademo/user/update3" --data-raw 'id=1&name=admin&password=B#2008_2108#es3'

## mybatis
curl  "http://localhost:8080/jpademo/depts"
curl -X PUT "http://localhost:8080/jpademo/dept/update" --data-raw 'id=1&departmentName=hhhhhh'

## mybatis与jpa混合使用
curl -X POST "http://localhost:8080/jpademo/user/update4" --data-raw 'id=1&name=admin&password=B#2008_2108#es3'

curl -X POST "http://localhost:8080/jpademo/user/update5" --data-raw 'id=1&name=admin&password=B#2008_2108#es5'

### 现场场景
curl -X POST "http://localhost:8080/jpademo/user/update6" --data-raw 'id=1&name=admin&password=B#2008_2108#es6'
curl -X POST "http://localhost:8080/jpademo/user/update7" --data-raw 'id=1&name=admin&password=B#2008_2108#es7'

### 测试仅mybatis回滚（应用自带数据源、中间件事务管理器）
curl  "http://localhost:8087/jpademo4/depts"
curl -X POST "http://localhost:8087/jpademo4/dept/updateonlymybatis"