package cn.hanquan.bootstrapcurd.controller;

import cn.hanquan.bootstrapcurd.entities.Department;
import cn.hanquan.bootstrapcurd.entities.User;
import cn.hanquan.bootstrapcurd.mapper.DepartmentMapper;
import cn.hanquan.bootstrapcurd.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentMapper departmentMapper;

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

    @GetMapping(value ="/user")
    public String get(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User u = userRepository.findById(user.getId()).get();
        logger.info("user=" + u);
        return "login";
    }
}
