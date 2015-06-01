package com.rent.web.action.admin;

import java.util.HashMap;
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

@Controller("Admin-RoleAtion")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> implements SessionAware{
	
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
	 * 跳到角色修改页面
	 * @return
	 */
	public String intoUpdate(){
		String userId=request.getParameter("userId");
		User user=userService.getEntity(User.class, userId);
		request.setAttribute("user", user);
		return "intoUpdate";
	}
	/**
	 * 修改用户角色
	 * @return
	 */
	public String update(){
		String userId=request.getParameter("userId");
		String roleId=model.getId();
		User user=userService.getEntity(User.class, userId);
		user.setRoleId(roleService.getEntity(Role.class, roleId));
		if(userService.update(user)){
			ajaxReturn("ok", "修改成功！", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "修改失败！", "error");
			return "jsonReturn";
		}
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
