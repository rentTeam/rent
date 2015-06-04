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
	
	//ajax json���ݱ���
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
	 * ��ҳ
	 * @return
	 */
	public String index(){
		return "index";
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
		if(!isExistUser()){
			user.setCollege(model.getCollege());
			user.setEmail(model.getEmail());
			user.setIdentity(model.getIdentity());
			user.setPassword(model.getPassword());
			user.setPhone(model.getPhone());
			user.setSchool(model.getSchool());
			user.setUserName(model.getUserName());
			user.setRoleId(roleService.getEntity(RoleInfo.class, 5));
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
				session.put("roleId", userList.get(0).getRoleId());
				session.put("id", userList.get(0).getId());
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
	 * ϵͳ�Զ���ӵĽ�ɫ����
	 */
	/*public UserAction(){
		if(null==roleService.findListByHql("form Role as role")){
			Role role =new Role();
			role.setId(1+"");
			role.setName("administrator");
			role.setDesc("ϵͳ����Ա");
			roleService.save(role);
			role.setId(2+"");
			role.setName("manager");
			role.setDesc("��ͨ����Ա");
			roleService.save(role);
			role.setId(3+"");
			role.setName("vip");
			role.setDesc("vip�û�");
			roleService.save(role);
			role.setId(4+"");
			role.setName("rent");
			role.setDesc("������û�");
			roleService.save(role);
			role.setId(5+"");
			role.setName("general");
			role.setDesc("��ͨ�û�");
			roleService.save(role);
		}
	}*/
	
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
