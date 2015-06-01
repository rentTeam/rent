package com.rent.web.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Role;
import com.rent.domin.User;
import com.rent.service.RoleService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Admin-UserAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	//ajax json数据变量
	private Map<String, Object> jsonData;
	
	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public Map<String, Object> getJsonData() {
		return jsonData;
	}


	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}
    
	/**
	 * 查看所有用户的基本信息
	 * @return
	 */
	public String query(){
		List<User> ulist=new ArrayList<User>();
		List<Role> rlist=new ArrayList<Role>();
		String hql="from User as user";
		int start=0;
		int limit=10;
		String[] params=null;
		if(null!=request.getParameter("start"))
			start=Integer.parseInt(request.getParameter("start"));
		if(null!=request.getParameter("limit"))
			limit=Integer.parseInt(request.getParameter("limit"));
		ulist=userService.queryForPages(hql, params, start, limit);
		for(User u:ulist){
			Role role=roleService.getEntity(Role.class, u.getRoleId().toString());
			rlist.add(role);
		}
		request.setAttribute("ulist", ulist);
		request.setAttribute("rlist", rlist);
		return "query";
	}
	
	/**
	 * 根据角色查询用户
	 * @return
	 */
	public String queryByRole(){
		List<User> ulist=new ArrayList<User>();
		String hql="from User where roleId=?";
		int start=0;
		int limit=10;
		String[] params=new String[1];
		params[0]=request.getParameter("roleId");
		if(null!=request.getParameter("start"))
			start=Integer.parseInt(request.getParameter("start"));
		if(null!=request.getParameter("limit"))
			limit=Integer.parseInt(request.getParameter("limit"));
		ulist=userService.queryForPages(hql, params, start, limit);
		request.setAttribute("ulist", ulist);
		return "queryByRole";
	}
	/**

     * ajax请求 json数据格式规范生成方法
     * @param status 响应状态
     * @param message 返回信息
     * @param data 返回数据
     */
    private void ajaxReturn(String status, String message, Object data) {
        jsonData = new HashMap<String, Object>();
        jsonData.put("status", status);
        jsonData.put("message", message);
        jsonData.put("data", data);
    }
}
