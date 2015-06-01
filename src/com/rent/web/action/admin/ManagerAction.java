package com.rent.web.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Car;
import com.rent.domin.Manager;
import com.rent.domin.Role;
import com.rent.service.ManagerService;
import com.rent.service.RoleService;
import com.rent.web.action.BaseAction;

@Controller("Admin-ManagerAction")
@Scope("prototype")
public class ManagerAction extends BaseAction<Manager> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private ManagerService managerService;
	
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
	 * 看是否有同名
	 * @return
	 */
	private boolean checkName(String userName){
		List<Manager> mlist=managerService.findListByHql("from Manager where userName=?", userName);
		if(mlist.isEmpty())
			return true;
		else
			return false;
	}
	/**
	 * 管理员登录
	 * @return
	 */
	public String login(){
		List<Manager> mlist=managerService.findListByHql("from Manager where userName=?", model.getUserName());
		if(mlist.isEmpty()){
			ajaxReturn("error", "登录失败，没有该用户", "error");
			return "jsonReturn";
		}else if(mlist.get(0).getPassword().equals(model.getPassword())){
			ajaxReturn("ok", "登录成功", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "登录失败，密码不正确", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 添加管理员信息
	 * @return
	 */
	public String add(){
		Manager manager=new Manager();
		if(checkName(model.getUserName())){
			manager.setIdentity(model.getIdentity());
			manager.setPassword(model.getPassword());
			manager.setUserName(model.getUserName());
			manager.setRoleId(roleService.getEntity(Role.class, model.getRoleId().toString()));
			if(managerService.save(manager)){
				ajaxReturn("ok", "添加成功", "ok");
				return "jsonReturn";
			}else{
				ajaxReturn("error", "添加失败", "error");
				return "jsonReturn";
			}
		}else{
			ajaxReturn("error", "添加失败，有同名", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 删除管理员信息
	 * @return
	 */
	public String delete(){
		Manager manager=managerService.getEntity(Manager.class, model.getId());
		if(managerService.delete(manager)){
			ajaxReturn("ok", "成功删除", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "删除失败", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 更新管理员信息
	 * @return
	 */
	public String update(){
		Manager manager=managerService.getEntity(Manager.class, model.getId());
		manager.setIdentity(model.getIdentity());
		manager.setPassword(model.getPassword());
		manager.setUserName(model.getUserName());
		manager.setRoleId(roleService.getEntity(Role.class, model.getRoleId().toString()));
		if(managerService.update(manager)){
			ajaxReturn("ok", "成功更新", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "更新失败", "error");
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
