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
import com.rent.service.CarService;
import com.rent.service.PictureCarService;
import com.rent.service.PictureService;
import com.rent.web.action.BaseAction;

@Controller("Home-CarAction")
@Scope("prototype")
public class CarAction extends BaseAction<Car> implements SessionAware{
	private Map<String, Object> session = null;
	
	@Resource
	private CarService carService;
	
	@Resource
	private PictureService pictureService;
	
	@Resource
	private PictureCarService pictureCarService;
	
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
