package com.rent.web.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Manager;
import com.rent.domin.Picture;
import com.rent.domin.PictureUser;
import com.rent.domin.User;
import com.rent.service.PictureCarService;
import com.rent.service.PictureManagerService;
import com.rent.service.PictureService;
import com.rent.service.PictureUserService;
import com.rent.web.action.BaseAction;

@Controller("Admin-PictureAction")
@Scope("prototype")
public class PictureAction extends BaseAction<Picture> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private PictureService pictureService;
	
	@Resource
	private PictureCarService pictureCarService;
	
	@Resource
	private PictureUserService pictureUserService;
	
	@Resource 
	private PictureManagerService pictureManagerService;
	
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
	 * 查看用户的图片
	 * @return
	 */
	public String queryUser(){
		List<Picture> identity=new ArrayList<Picture>();
		List<Picture> student=new ArrayList<Picture>();
		List<Picture> other=new ArrayList<Picture>();
		String hql01="from Picture where type='identity' and id id in("
				+ "select pictureId form PictureUser where userId=?)";
		String hql02="from Picture where type='student' and id id in("
				+ "select pictureId form PictureUser where userId=?)";
		String hql03="from Picture where type='other' and id id in("
				+ "select pictureId form PictureUser where userId=?)";
		identity=pictureService.findListByHql(hql01, request.getParameter("userId"));
		student=pictureService.findListByHql(hql02, request.getParameter("userId"));
		other=pictureService.findListByHql(hql03, request.getParameter("userId"));
		request.setAttribute("identity", identity);
		request.setAttribute("student", student);
		request.setAttribute("other", other);
		return "queryUser";
	}
	
	/**
	 * 查看管理员的照片
	 * @return
	 */
	public String queryManager(){
		List<Picture> identity=new ArrayList<Picture>();
		String managerId=request.getParameter("managerId");
		String hql01="from Picture where type='identity' and id id in("
				+ "select pictureId form PictureUser where managerId=?)";
		identity=pictureService.findListByHql(hql01, managerId);
		request.setAttribute("identity", identity);
		return "queryManager";
	}
	
	/**
	 * 查看车的图片
	 * @return
	 */
	public String queryCar(){
		List<Picture> cp=new ArrayList<Picture>();
		String carId=request.getParameter("carId");
		String hql01="from Picture where type='car' and id is in("
				+ "select pictureId form PictureUser where carId=?)";
		cp=pictureService.findListByHql(hql01, carId);
		request.setAttribute("cp", cp);
		return "queryCar";
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
