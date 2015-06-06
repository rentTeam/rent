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
	
	//ajax json���ݱ���
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
	 * ��ҳ
	 * @return
	 */
	public String index(){
		return "home";
	}
	
	/**
	 * �鿴������Ϣ
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
	 * �û�ע��ҳ��
	 * @return
	 */
	public String userRegister(){
		return "userRegister";
	}
	
	/**
	 * �ж��Ƿ���ڴ��û�
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
	 * �û�ע��
	 * @return
	 */
	public String add(){
		User user=new User();
		List<RoleInfo> rlist=new ArrayList<RoleInfo>();
		rlist=roleService.findListByHql("from RoleInfo as role");
		if(rlist.isEmpty()){//ϵͳ�Զ���ӵĽ�ɫ����
			RoleInfo role =new RoleInfo();
			role.setRoleName("administrator");
			role.setStatus("ϵͳ����Ա");
			roleService.save(role);
			role.setRoleName("manager");
			role.setStatus("��ͨ����Ա");
			roleService.save(role);
			role.setRoleName("vip");
			role.setStatus("vip�û�");
			roleService.save(role);
			role.setRoleName("rent");
			role.setStatus("������û�");
			roleService.save(role);
			role.setRoleName("general");
			role.setStatus("��ͨ�û�");
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
			this.ajaxReturn("ok", "ע��ɹ�", "ok");
			return "jsonReturn";
		}else{
			this.ajaxReturn("error", "���д��û���", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * �û���¼ҳ��
	 * @return
	 */
	public String intoLogin(){
		return "userLogin";
	}
	
	/**
	 * �û���¼��֤
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
				ajaxReturn("ok", "��¼�ɹ�", "ok");
				return "jsonReturn";
			}else{
				this.ajaxReturn("error", "��¼ʧ�ܣ��������", "error");
				return "jsonReturn";
			}
		}else{
			this.ajaxReturn("error", "���û�������", "error");
			return "jsonReturn";
		}
	}
	
	
	/**
     * ajax���� json���ݸ�ʽ�淶���ɷ���
     * @param status ��Ӧ״̬
     * @param message ������Ϣ
     * @param data ��������
     */
    private void ajaxReturn(String status, String message, Object data) {
        jsonData = new HashMap<String, Object>();
        jsonData.put("status", status);
        jsonData.put("message", message);
        jsonData.put("data", data);
    }
}
