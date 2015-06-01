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
	 * ���Ƿ���ͬ��
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
	 * ����Ա��¼
	 * @return
	 */
	public String login(){
		List<Manager> mlist=managerService.findListByHql("from Manager where userName=?", model.getUserName());
		if(mlist.isEmpty()){
			ajaxReturn("error", "��¼ʧ�ܣ�û�и��û�", "error");
			return "jsonReturn";
		}else if(mlist.get(0).getPassword().equals(model.getPassword())){
			ajaxReturn("ok", "��¼�ɹ�", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "��¼ʧ�ܣ����벻��ȷ", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ��ӹ���Ա��Ϣ
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
				ajaxReturn("ok", "��ӳɹ�", "ok");
				return "jsonReturn";
			}else{
				ajaxReturn("error", "���ʧ��", "error");
				return "jsonReturn";
			}
		}else{
			ajaxReturn("error", "���ʧ�ܣ���ͬ��", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ɾ������Ա��Ϣ
	 * @return
	 */
	public String delete(){
		Manager manager=managerService.getEntity(Manager.class, model.getId());
		if(managerService.delete(manager)){
			ajaxReturn("ok", "�ɹ�ɾ��", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "ɾ��ʧ��", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ���¹���Ա��Ϣ
	 * @return
	 */
	public String update(){
		Manager manager=managerService.getEntity(Manager.class, model.getId());
		manager.setIdentity(model.getIdentity());
		manager.setPassword(model.getPassword());
		manager.setUserName(model.getUserName());
		manager.setRoleId(roleService.getEntity(Role.class, model.getRoleId().toString()));
		if(managerService.update(manager)){
			ajaxReturn("ok", "�ɹ�����", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "����ʧ��", "error");
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
