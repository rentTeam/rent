package com.rent.web.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Car;
import com.rent.domin.Manage;
import com.rent.domin.Picture;
import com.rent.domin.RoleInfo;
import com.rent.service.ManagerService;
import com.rent.service.PictureService;
import com.rent.service.RoleService;
import com.rent.web.action.BaseAction;

@Controller("Admin-ManagerAction")
@Scope("prototype")
public class ManagerAction extends BaseAction<Manage> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private ManagerService managerService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PictureService pictureService;
	
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
		List<Manage> mlist=managerService.findListByHql("from Manager where userName=?", userName);
		if(mlist.isEmpty())
			return true;
		else
			return false;
	}
	/**
	 * 管理员登录页面
	 * @return
	 */
	public String showLogin(){
		return "intoLogin";
	}
	/**
	 * 管理员登录
	 * @return
	 */
	public String login(){
		List<Manage> mlist=managerService.findListByHql("from Manage where userName=?", model.getUserName());
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
	 * 跳到首页
	 * @return
	 */
	public String index(){
		return "managerLogin";
	}
	/**
	 * 退出登录
	 * @return
	 */
	public String logout(){
		session.clear();
		return "intoLogin";
	}
	/**
	 * 跳到管理员添加页面
	 * @return
	 */
	public String intoAdd(){
		return "intoAdd";
	}
	/**
	 * 添加管理员信息
	 * @return
	 */
	public String add(){
		Manage manager=new Manage();
		if(checkName(model.getUserName())){
			manager.setIdentity(model.getIdentity());
			manager.setPassword(model.getPassword());
			manager.setUserName(model.getUserName());
			manager.setRoleId(model.getRoleId());
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
	 * 查询现有管理员
	 * @return
	 */
	public String query(){
		List<Manage> mlist=new ArrayList<Manage>();
		List<Picture> plist=new ArrayList<Picture>();
		List<Picture> ps=new ArrayList<Picture>();
		String s=request.getParameter("start");
		String l=request.getParameter("limit");
		int start=0;
		int limit=10;
		String[] params=null;
		if(null!=s)start=Integer.parseInt(s);
		if(null!=l)limit=Integer.parseInt(l);
		mlist=managerService.queryForPages("from Manager as manager", params, start, limit);
		String hql="from Picture where type='manager' and id="
				+ "(secect pictureId from pictureManager where managerId=?)";
		for(Manage m:mlist){
			ps=pictureService.findListByHql(hql,m.getId());
			if(!plist.isEmpty())
				plist.add(ps.get(0));
		}
		request.setAttribute("mlist", mlist);
		request.setAttribute("plist", plist);
		return "query";
	}
	/**
	 * 删除管理员信息
	 * @return
	 */
	public String delete(){
		Manage manager=managerService.getEntity(Manage.class, model.getId());
		if(managerService.delete(manager)){
			String hql="from Picture where id is in("
					+ "select pictureId from PictureManager where managerId=?";
			List<Picture> plist=new ArrayList<Picture>();
			plist=pictureService.findListByHql(hql, manager.getId());
			for(Picture p:plist){
				String url=p.getUrl();
				pictureService.delete(p);
				File file =new File(url);
				file.delete();
			}
			ajaxReturn("ok", "成功删除", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "删除失败", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 跳到管理员信息更新页
	 * @return
	 */
	public String intoUpdate(){
		Manage manager=managerService.getEntity(Manage.class, model.getId());
		request.setAttribute("manager", manager);
		return "intoUpdate";
	}
	/**
	 * 更新管理员信息
	 * @return
	 */
	public String update(){
		Manage manager=managerService.getEntity(Manage.class, model.getId());
		manager.setIdentity(model.getIdentity());
		manager.setPassword(model.getPassword());
		manager.setUserName(model.getUserName());
		manager.setRoleId(model.getRoleId());
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
