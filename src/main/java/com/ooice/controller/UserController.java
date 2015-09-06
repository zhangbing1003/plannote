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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ooice.model.User;
import com.ooice.model.search.SearchResult;
import com.ooice.service.UserService;
import com.ooice.service.search.JSoupBaiduSearcher;
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
		System.out.println("测试。。。");
		
		JSoupBaiduSearcher jsb = new JSoupBaiduSearcher();
		SearchResult  sr = jsb.search("av");
		System.out.println(sr);
		return "index";
	}
	
	/**
	 * 主页数据加载
	 * @param response
	 * @return
	 * @throws Exception   
	 * @return 
	 * @author ICE
	 * @date 2015年9月5日下午10:24:01
	 */
	@RequestMapping("index")
	@ResponseBody
	public String index(HttpServletResponse response) throws Exception{
		CommonUtil.setAjaxResponseAttr(response);
		PrintWriter out = null;
		out = response.getWriter();
		//查看是否登陆，如果登陆
		
		JSoupBaiduSearcher jsb = new JSoupBaiduSearcher();
		SearchResult  sr = jsb.search("ooice");
		out.print(JsonUtil.bean2Json(sr));
		return null;
	}

	/**
	 * 提交新计划
	 * @param response
	 * @return
	 * @throws Exception   
	 * @return 
	 * @author ICE
	 * @date 2015年9月5日下午10:23:46
	 */
	@RequestMapping("addNewPlanNote")
	@ResponseBody
	public String addNewPlanNote(HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		CommonUtil.setAjaxResponseAttr(response);
		PrintWriter out = null;
		out = response.getWriter();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Map map = new HashMap();
		map.put("success", true);
		map.put("data", "");
		
		out.print(JsonUtil.map2Json(map));
		return null;
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
