package com.rent.web.action.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Car;
import com.rent.domin.Picture;
import com.rent.domin.RoleInfo;
import com.rent.domin.User;
import com.rent.service.CarService;
import com.rent.service.PictureService;
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
	
	@Resource
	private CarService carService;
	
	@Resource
	private PictureService pictureService;
	
	
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
		return "home";
	}
	
	/**
	 * 查看车的信息
	 * @return
	 */
	public String query(){
		List<Picture> plist=new ArrayList<Picture>();
		List<Picture> plists=new ArrayList<Picture>();
		List<Car> clist=new ArrayList<Car>();
		String s=request.getParameter("start");
		String l=request.getParameter("limit");
		int start=0;
		int limit=10;
		String[] params=null;
		if(null!=s)start=Integer.parseInt(s);
		if(null!=l)limit=Integer.parseInt(l);
		clist=carService.queryForPages("from Car as car", params, start, limit);
		String hql="from Picture where type='car' and id="
				+ "(secect pictureId from pictureCar where carId=?)";
		for(Car car:clist){
			plist=pictureService.findListByHql(hql, car.getCarId());
			if(!plist.isEmpty())
				plists.add(plist.get(0));
		}
		request.setAttribute("clist", clist);
		request.setAttribute("plists", plists);
		return "query";
		
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
		List<RoleInfo> rlist=new ArrayList<RoleInfo>();
		rlist=roleService.findListByHql("from RoleInfo as role");
		if(rlist.isEmpty()){//系统自动添加的角色属性
			RoleInfo role =new RoleInfo();
			role.setRoleName("administrator");
			role.setStatus("系统管理员");
			roleService.save(role);
			role.setRoleName("manager");
			role.setStatus("普通管理员");
			roleService.save(role);
			role.setRoleName("vip");
			role.setStatus("vip用户");
			roleService.save(role);
			role.setRoleName("rent");
			role.setStatus("可租借用户");
			roleService.save(role);
			role.setRoleName("general");
			role.setStatus("普通用户");
			roleService.save(role);
		}
		rlist=roleService.findListByHql("from RoleInfo as role where roleName=?","general");
		if(!isExistUser()){
			user.setCollege(model.getCollege());
			user.setEmail(model.getEmail());
			user.setIdentity(model.getIdentity());
			user.setPassword(model.getPassword());
			user.setPhone(model.getPhone());
			user.setSchool(model.getSchool());
			user.setUserName(model.getUserName());
			for(RoleInfo r:rlist){
				user.setRoleId(r);
				System.out.println("roleName->"+r.getRoleName());
			}
			userService.save(user);
			List<User> userList=userService.findListByHql("from User where userName=?", model.getUserName());
			if(!userList.isEmpty()&&!rlist.isEmpty()){
				session.put("id", userList.get(0).getId());
				session.put("roleId",rlist.get(0).getId());
				System.out.println("USERName->"+userList.get(0).getUserName());
			}
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
				session.put("roleId", userList.get(0).getRoleId().getId());
				session.put("id", userList.get(0).getId());
				System.out.println("roleId->"+userList.get(0).getRoleId().getId());
				System.out.println("userName->"+userList.get(0).getUserName());
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
