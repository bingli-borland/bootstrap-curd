package cn.hanquan.bootstrapcurd.controller;

import cn.hanquan.bootstrapcurd.entities.Department;
import cn.hanquan.bootstrapcurd.entities.User;
import cn.hanquan.bootstrapcurd.mapper.DepartmentMapper;
import cn.hanquan.bootstrapcurd.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//@RestController
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    TransactionManager transactionManager;

    @Autowired
    DataSource dataSource;

    @PostMapping(value ="/user")
    public String insertUser(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User saveUser = userRepository.save(user); // 存到数据库
        logger.info("user=" + user);
        logger.info("saveUser=" + saveUser);
        return "login";
    }

    @PostMapping(value ="/user/update")
    @Transactional(rollbackFor = Exception.class)
    public String update(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User saveUser = userRepository.save(user);
        logger.info("user=" + user);
        logger.info("saveUser=" + saveUser);
        user.setPassword("1111111111111111111111111111111111");
        saveUser = userRepository.save(user);
        logger.info("after user=" + user);
        logger.info("after saveUser=" + saveUser);
        return "login";
    }

    @PostMapping(value ="/user/update2")
    public String update2(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User saveUser = userRepository.save(user);
        logger.info("user=" + user);
        logger.info("saveUser=" + saveUser);
        user.setPassword("1111111111111111111111111111111111");
        saveUser = userRepository.save(user);
        logger.info("after user=" + user);
        logger.info("after saveUser=" + saveUser);
        return "login";
    }

    @PostMapping(value ="/user/update3")
    @Transactional
    public String update3(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User saveUser = userRepository.save(user);
        logger.info("user=" + user);
        logger.info("saveUser=" + saveUser);
        user.setPassword("1111111111111111111111111111111111");
        saveUser = userRepository.save(user);
        logger.info("after user=" + user);
        logger.info("after saveUser=" + saveUser);
        return "login";
    }

    @PostMapping(value ="/user/update4")
    @Transactional
    public String update4(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("hhhhhhh");
        departmentMapper.updateDept(department);
        user.setPassword("1111111111111111111111111111111111");
        userRepository.save(user);
        return "login";
    }

    @PostMapping(value ="/user/update5")
    @Transactional
    public String update5(User user) {
        userRepository.save(user);
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("1111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111");
        departmentMapper.updateDept(department);
        return "login";
    }

    @PostMapping(value ="/user/update6")
    @Transactional
    public String update6(User user) {
        userRepository.save(user);
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("mmmmmm");
        departmentMapper.updateDept(department);
        throw new RuntimeException("模拟异常");
    }
    @PostMapping(value ="/user/update7")
    @Transactional
    public String update7(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("nnnnnn");
        departmentMapper.updateDept(department);
        userRepository.save(user);
        throw new RuntimeException("模拟异常2");
    }

    @GetMapping(value ="/user")
    public String get(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User u = userRepository.findById(user.getId()).get();
        logger.info("user=" + u);
        return "login";
    }

    @PostMapping(value ="/user/reset")
    @Transactional
    public String reset(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("develop");
        departmentMapper.updateDept(department);
        userRepository.saveAndFlush(user);
        System.out.println("User{id=" + user.getId() + ", name=" + user.getName()  + ", password=" + user.getPassword() + "}");
        System.out.println("Department{id=1, departmentName=develop}");
        return "dept/reset";
    }

    @PostMapping(value ="/user/validation")
    @Transactional
    public String validation(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String deptId = request.getParameter("deptId");
        String deptName = request.getParameter("deptName");
        String jta = request.getParameter("jta");
        String appds = request.getParameter("appds");
        System.out.println("TransactionManager=" + transactionManager.getClass());
        System.out.println("DataSource=" + dataSource.getClass());
        System.out.println("User{id=" + id + ", name=" + name  + ", password=" + password + "}");
        System.out.println("Department{id=" + deptId + ", departmentName=" + deptName + "}");
        if ("true".equals(appds) && !"com.alibaba.druid.pool.DruidDataSource".equals(dataSource.getClass().getName())) {
            return "dept/failed";
        }
        if ("true".equals(jta) && "org.springframework.orm.jpa.JpaTransactionManager".equals(transactionManager.getClass().getName())) {
            return "dept/failed";
        }
        User user = userRepository.findByNameAndPassword(name, password);
        if (user != null && id.equals(String.valueOf(user.getId()))){
            Department department = departmentMapper.getDeptById(Integer.parseInt(deptId));
            if (deptName.equals(department.getDepartmentName())) {
                return "dept/pass";
            }
        }
        return "dept/failed";
    }

    @PostMapping(value ="/user/test1")
    @Transactional
    public String test1(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("test1");
        departmentMapper.updateDept(department);
        userRepository.save(user);
        throw new RuntimeException("模拟异常test1");
    }

    @PostMapping(value ="/user/test2")
    @Transactional
    public String test2(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("test2");
        departmentMapper.updateDept(department);
        userRepository.saveAndFlush(user);
        throw new RuntimeException("模拟异常test2");
    }

    @PostMapping(value ="/user/test3")
    @Transactional
    public String test3(User user) {
        userRepository.save(user);
        throw new RuntimeException("模拟异常test3");
    }

    @PostMapping(value ="/user/test4")
    @Transactional
    public String test4(User user) {
        userRepository.saveAndFlush(user);
        throw new RuntimeException("test4");
    }

    @PostMapping(value ="/user/test5")
    @Transactional
    public String test5(User user) {
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("test5");
        departmentMapper.updateDept(department);
        throw new RuntimeException("test5");
    }
}
