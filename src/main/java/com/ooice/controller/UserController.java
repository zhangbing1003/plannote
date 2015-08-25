package com.ooice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.ooice.model.User;
import com.ooice.service.UserService;
import com.ooice.util.CommonUtil;
import com.ooice.util.JsonUtil;

/**
 * 用户操作
 * @Title: UserController.java
 * @Package com.ooice.controller
 * @Description: TODO
 * @author ICE
 * @date 2015年8月22日上午12:35:07
 */
@Controller
@Scope("prototype")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("userList")
	public String userList(){
		
		return "user/userList";
	}
	
	 /**
	  * 添加用户(该方法用来测试ajax正常调用)
	  * @param request
	  * @param response
	  * @return   
	  * @return 
	  * @author ICE
	  * @date 2015年8月22日上午12:34:18
	  */
    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(HttpServletRequest request,
            HttpServletResponse response) {
        User user = new User();
        user.setName("ice");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            System.out.println("ceshi");
            map.put("flag", true);
        } catch (Exception e) {
            map.put("flag", false);
        }
        return map;
    }
    
    /**
     * 获取用户信息
     * @param  request
     * @param  response
     * @param 
     * @param  Exception   
     * @return 
     * @author ICE
     * @date 2015年8月22日上午12:32:26
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public String getUserList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	CommonUtil.setAjaxResponseAttr(response);
    	PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> userList = userService.selectUserList();
        map.put("userList", userList);
        
        out.print(JsonUtil.collection2Json(userList));
        return null;
    }
    
    
    
	
}
