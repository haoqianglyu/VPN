package com.haoqiang.vpn.api;

import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.repository.UserDao;
import com.haoqiang.vpn.repository.UserDaoImpl;
import com.haoqiang.vpn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-12 10:48
 */

@RequestMapping(value = {"/api/users","/api/user"})
@Controller
@ResponseBody //return
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    // /api/users Get
    @RequestMapping(method = RequestMethod.GET)
    public List getUserList(){
        logger.debug("list users");
        return userService.findAll();
    }

    // /api/users/5/paystub/10/row/1   Get      /object/object_id
    @RequestMapping(value = "/{Id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("Id") Long Id){
        logger.debug("find users id:"+Id);

        return userService.findById(Id);
    }

    // /api/users Post
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User addUser(@RequestBody User u){
        userService.createUser(u);
        return u;
    }

    // /api/users?username=ares Post
    @RequestMapping(value = "", method = RequestMethod.GET,params = "username")
    public List<User> getUserByUsername(@RequestParam("username") String username){
        logger.debug("find users by username:"+username);
        return userService.findByUsernameIgnoreCase(username);
    }
}
