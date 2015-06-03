package com.rent.web.action.admin;

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
import com.rent.domin.PictureCar;
import com.rent.domin.RentInfo;
import com.rent.domin.User;
import com.rent.service.CarService;
import com.rent.service.PictureCarService;
import com.rent.service.PictureService;
import com.rent.service.RentInfoService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Admin-RentInfoAction")
@Scope("prototype")
public class RentInfoAction extends BaseAction<RentInfo> implements SessionAware{
	private Map<String, Object> session = null;
	
	@Resource
	private RentInfoService rentInfoService;
	
	@Resource
	private PictureService pictureService;
	
	@Resource
	private PictureCarService pictureCarService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CarService carService;
	
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
	 * 根据出租的初始时间查询出租表
	 * @return
	 */
	public String query(){
		List<RentInfo> rlist=new ArrayList<RentInfo>();
		List<Picture> plist=new ArrayList<Picture>();
		List<User> ulist=new ArrayList<User>();
		List<Car> clist=new ArrayList<Car>();
		int start=0;
		int limit=10;
		String[] params=null;
		if(null!=request.getParameter("start"))
			start=Integer.parseInt(request.getParameter("start"));
		if(null!=request.getParameter("limit"))
			limit=Integer.parseInt(request.getParameter("limit"));
		String hql="from RentInfo group by start asc";
		rlist=rentInfoService.queryForPages(hql, params, start, limit);
		for(RentInfo r:rlist){
			PictureCar pc=pictureCarService.getEntity(PictureCar.class, r.getCarId().toString());
			Picture p=pictureService.getEntity(Picture.class, pc.getPictureId().toString());
			User user=userService.getEntity(User.class, r.getUserId().toString());
			Car car=carService.getEntity(Car.class, r.getCarId().toString());
			plist.add(p);
			ulist.add(user);
			clist.add(car);
		}
		request.setAttribute("rlist", rlist);
		request.setAttribute("plist", plist);
		request.setAttribute("ulist", ulist);
		request.setAttribute("clist", clist);
		return "query";
	}
	
	/**
	 * 删除出租信息表
	 * @return
	 */
	public String delete(){
		String hql="from RentIfno where carId=? and userId=?";
		List<RentInfo> rentInfos=rentInfoService.findListByHql(hql, model.getCarId(),model.getUserId());
		if(!rentInfos.isEmpty()){
			if(rentInfoService.delete(rentInfos.get(0))){
				ajaxReturn("ok", "删除成功", "ok");
				return "jsonReturn";
			}else{
				ajaxReturn("error", "删除失败", "error");
				return "jsonReturn";
			}
		}
		ajaxReturn("error", "删除失败", "error");
		return "jsonReturn";
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
