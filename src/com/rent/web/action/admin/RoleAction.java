package com.rent.web.action.admin;

import java.util.HashMap;
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

@Controller("Admin-RoleAtion")
@Scope("prototype")
public class RoleAction extends BaseAction<RoleInfo> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	//ajax json���ݱ���
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
	 * ������ɫ�޸�ҳ��
	 * @return
	 */
	public String intoUpdate(){
		String userId=request.getParameter("userId");
		User user=userService.getEntity(User.class, userId);
		request.setAttribute("user", user);
		return "intoUpdate";
	}
	/**
	 * �޸��û���ɫ
	 * @return
	 */
	public String update(){
		String userId=request.getParameter("userId");
		String roleId=model.getId();
		User user=userService.getEntity(User.class, userId);
		user.setRoleId(roleService.getEntity(RoleInfo.class, roleId));
		if(userService.update(user)){
			ajaxReturn("ok", "�޸ĳɹ���", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "�޸�ʧ�ܣ�", "error");
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
