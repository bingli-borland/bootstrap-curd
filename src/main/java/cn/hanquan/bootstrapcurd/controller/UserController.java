package cn.hanquan.bootstrapcurd.controller;

import cn.hanquan.bootstrapcurd.entities.User;
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

    @GetMapping(value ="/user")
    public String get(User user) {
        Logger logger = LoggerFactory.getLogger(getClass());
        User u = userRepository.findById(user.getId()).get();
        logger.info("user=" + u);
        return "login";
    }
}
