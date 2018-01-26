package com.gnnt.admin.controller;

import com.gnnt.admin.dao.UserDao;
import com.gnnt.admin.model.User;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述：控制器
 *  需要有视图渲染的， @Controller
 *  请求数据的,@RestController
 * add by 符晶晶
 * 2018-01-26 13:51
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping("/list")
    public String listUser(Model model) {
        List<User> userList = null;
        userList = userDao.getUserList();
        model.addAttribute("retLsit", userList);
        return "admin";
    }

    /**
     * 根据id查询User实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = null;

        user = userDao.getUserById(id);

        return user;
    }

    /**
     * 保存user实体
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int insertUser(User user) {
        int res = 1;

        res = userDao.saveUser(user);

        return res;
    }

    /**
     * 保存User实体-PreparedStatementSetter
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveWithSafe", method = RequestMethod.POST)
    public int insertUserWithSafe(User user) {
        int res = 1;

        res = userDao.saveUserWithSafe(user);

        return res;
    }

    /**
     * 保存user实体-PreparedStatementCreator、KeyHolder-保存实体后返回实体的主键
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveWithKey", method = RequestMethod.POST)
    public int insertUserWithKey(User user) {
        int res = 1;

        res = userDao.saveUserWithKey(user);

        return res;
    }

    /**
     * 根据id更新user实体
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public int updateUserWithId(@PathVariable Integer id, HttpServletRequest request) {
        int res = 1;

        if (id != null && !id.equals(0)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            User updateUser = new User(id, Strings.isNullOrEmpty(name) ? null : name, Strings.isNullOrEmpty(email) ? null : email);
            res = userDao.updateUser(updateUser);
        }

        return res;
    }

    /**
     * 根据id删除user实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public int deleteUserById(@PathVariable Integer id) {
        int res = 1;

        User deleteUser = userDao.getUserById(id);
        res = userDao.deleteUser(deleteUser);

        return res;
    }

    /**
     * 根据name查询是否存在某个user实体
     *
     * @param request
     * @return
     */
    @RequestMapping("/isExistUser")
    public Boolean isExistUser(HttpServletRequest request) {
        Boolean res = false;
        String name = request.getParameter("name");

        User queryUser = new User(null, Strings.isNullOrEmpty(name) ? null : name, null);
        User deleteUser = userDao.getUserByUserName(queryUser);
        if (deleteUser != null) {
            res = true;
        }
        return res;
    }

    /**
     * 查询user实体的总数
     *
     * @return
     */
    @RequestMapping("/total")
    public Integer getTotal() {
        Integer res = 0;

        res = userDao.getCount();

        return res;
    }

}
