package com.rent.web.action.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.RoleInfo;
import com.rent.domin.User;
import com.rent.service.RoleService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Home-UserAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> implements SessionAware{
	

    private Map<String, Object> session = null;
	
	//ajax json数据变量
	private Map<String, Object> jsonData;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	
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
	 * 首页
	 * @return
	 */
	public String index(){
		return "index";
	}
	/**
	 * 用户注册页面
	 * @return
	 */
	public String userRegister(){
		return "userRegister";
	}
	
	/**
	 * 判断是否存在此用户
	 * @return
	 */
	private boolean isExistUser(){
		List<User> userList=userService.findListByHql("from User where userName=?", model.getUserName());
		if(userList.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String add(){
		User user=new User();
		if(!isExistUser()){
			user.setCollege(model.getCollege());
			user.setEmail(model.getEmail());
			user.setIdentity(model.getIdentity());
			user.setPassword(model.getPassword());
			user.setPhone(model.getPhone());
			user.setSchool(model.getSchool());
			user.setUserName(model.getUserName());
			user.setRoleId(roleService.getEntity(RoleInfo.class, 5));
			this.ajaxReturn("ok", "注册成功", "ok");
			return "jsonReturn";
		}else{
			this.ajaxReturn("error", "已有此用户名", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 用户登录页面
	 * @return
	 */
	public String intoLogin(){
		return "userLogin";
	}
	
	/**
	 * 用户登录验证
	 * @return
	 */
	public String check(){
		List<User> userList=userService.findListByHql("from User where userName=?", model.getUserName());
		if(!userList.isEmpty()){
			if(userList.get(0).getPassword().equals(model.getPassword())){
				session.put("roleId", userList.get(0).getRoleId());
				session.put("id", userList.get(0).getId());
				ajaxReturn("ok", "登录成功", "ok");
				return "jsonReturn";
			}else{
				this.ajaxReturn("error", "登录失败，密码错误！", "error");
				return "jsonReturn";
			}
		}else{
			this.ajaxReturn("error", "该用户不存在", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 系统自动添加的角色属性
	 */
	/*public UserAction(){
		if(null==roleService.findListByHql("form Role as role")){
			Role role =new Role();
			role.setId(1+"");
			role.setName("administrator");
			role.setDesc("系统管理员");
			roleService.save(role);
			role.setId(2+"");
			role.setName("manager");
			role.setDesc("普通管理员");
			roleService.save(role);
			role.setId(3+"");
			role.setName("vip");
			role.setDesc("vip用户");
			roleService.save(role);
			role.setId(4+"");
			role.setName("rent");
			role.setDesc("可租借用户");
			roleService.save(role);
			role.setId(5+"");
			role.setName("general");
			role.setDesc("普通用户");
			roleService.save(role);
		}
	}*/
	
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
