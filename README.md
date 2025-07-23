# bootstrap-curd
基于SpringBoot，整合MyBatis, Hibernate, JPA, Druid, bootstrap, thymeleaf等，进行增删改查操作、文件上传、文件下载、excel生成的Demo


- 默认开启8087端口，项目基础路径为/curd，可在application.properties中更改

- 运行后，访问`http://主机ip:端口号/curd/`即跳转到注册登录页面

![employee](https://img-blog.csdnimg.cn/20200130082937376.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9oYW5xdWFuLmJsb2cuY3Nkbi5uZXQ=,size_16,color_FFFFFF,t_70)

curl -X POST "http://localhost:8087/jpademo/user" --data-raw 'id=1&name=admin&password=B#2008_2108#es'

curl http://localhost:8087/jpademo/user?id=1

## jpa
### 增加事务注解并加参数，回滚成功
curl -X POST "http://localhost:8087/jpademo/user/update"  --data-raw 'id=1&name=admin&password=B#2008_2108#es1'

### 不加事务注解，回滚失败
curl -X POST "http://localhost:8087/jpademo/user/update2" --data-raw 'id=1&name=admin&password=B#2008_2108#es2'

### 增加事务注解不加参数，回滚成功
curl -X POST "http://localhost:8087/jpademo/user/update3" --data-raw 'id=1&name=admin&password=B#2008_2108#es3'

## mybatis
curl  "http://localhost:8087/jpademo/depts"
curl -X PUT "http://localhost:8087/jpademo/dept/update" --data-raw 'id=1&departmentName=hhhhhh'

## mybatis与jpa混合使用
curl -X POST "http://localhost:8087/jpademo/user/update4" --data-raw 'id=1&name=admin&password=B#2008_2108#es3'

curl -X POST "http://localhost:8087/jpademo/user/update5" --data-raw 'id=1&name=admin&password=B#2008_2108#es5'

### 现场场景
curl -X POST "http://localhost:8087/jpademo/user/update6" --data-raw 'id=1&name=admin&password=B#2008_2108#es6'
curl -X POST "http://localhost:8087/jpademo/user/update7" --data-raw 'id=1&name=admin&password=B#2008_2108#es7'

| 场景                                                                                                                                                                                                                                                                                                                                                                                                                                                    | 中间件数据源 | 中间件事务管理器 | Jpa(Hibernate)回滚情况 |     Mybatis回滚情况 |
|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|----------|-------------------|----------------:|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test1' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=test1&appds=true&jta=true'       |否|是|回滚成功（save操作）|回滚失败|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test2' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=test2&deptId=1&deptName=test2&appds=true&jta=true'         |否|是|回滚失败（saveAndFlush操作）|回滚失败|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test3" --data-raw 'id=1&name=admin&password=test3' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=true'     |否|是|回滚成功（save操作）|无|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test4" --data-raw 'id=1&name=admin&password=test4' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=test4&deptId=1&deptName=develop&appds=true&jta=true'       |否|是|回滚失败（saveAndFlush操作）|无|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test5" --data-raw 'id=1&name=admin&password=test5' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=test5&appds=true&jta=true'       |否|是|无|回滚失败|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=false <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test11' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=false'  |否|否|回滚成功（save操作）|回滚成功|
| 系统属性-Dspring.profiles.active=appds -Dspring.jta.enabled=false <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test22' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=false'  |否|否|回滚成功（saveAndFlush操作）|回滚成功|
| 系统属性-Dspring.profiles.active=jndids -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test111' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=false&jta=true' |是|是|回滚成功（save操作）|回滚成功|
| 系统属性-Dspring.profiles.active=jndids -Dspring.jta.enabled=true <br/> curl -X POST "http://localhost:8087/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' <br/> curl -X POST "http://localhost:8087/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test222' <br/> curl -X POST "http://localhost:8087/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=false&jta=true' |是|是|回滚成功（saveAndFlush操作）|回滚成功|


