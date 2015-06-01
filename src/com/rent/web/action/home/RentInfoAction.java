package com.rent.web.action.home;

import java.sql.Timestamp;
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
import com.rent.domin.RentInfo;
import com.rent.domin.User;
import com.rent.service.CarService;
import com.rent.service.PictureService;
import com.rent.service.RentInfoService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Home-RentInfoAction")
@Scope("prototype")
public class RentInfoAction extends BaseAction<RentInfo> implements SessionAware{
private Map<String, Object> session = null;
	
	//ajax json数据变量
	private Map<String, Object> jsonData;
	
	@Resource
	private RentInfoService rentInfoService;
	
	@Resource
	private CarService carService;
	
	@Resource
	private UserService userService;
	
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
	 * 跳转到租车页面
	 * @return
	 */
    public String intoRent(){
    	String hql="from Picture where id="
    			+ "(select pictureId from PictureCar where carId=?)";
    	String id=request.getParameter("carId");
    	Car car=carService.getEntity(Car.class,id);
    	List<Picture> plist=pictureService.findListByHql(hql, car.getCarId());
    	request.setAttribute("car", car);
    	request.setAttribute("picture", plist);
    	return "intoRent";
    }
	/**
	 * 添加出租信息
	 * @return
	 */
	public String add(){
		RentInfo rent=new RentInfo();
		Timestamp createTime=new Timestamp(System.currentTimeMillis());
		if(null==session){
			ajaxReturn("error", "没有登录!", "session");
			return "jsonReturn";
		}else if(session.get("roleId").equals("5")){
			ajaxReturn("error", "没有租借权利，需要通过审核!", "session");
			return "jsonReturn";
		}else{
			rent.setCarId(model.getCarId());
			rent.setStart(createTime);
			rent.setTimeLimit(model.getTimeLimit());
			rent.setUserId(userService.getEntity(User.class, session.get("id").toString()));
			rentInfoService.save(rent);
			ajaxReturn("ok", "租借成功!", "session");
			return "jsonReturn";
		}
	}
	
	/**
	 * 查看租借信息
	 * @return
	 */
	public String query(){
			List<RentInfo> rent=new ArrayList<RentInfo>();
		if(null==session){
			ajaxReturn("error", "没有登录!", "session");
			return "jsonReturn";
		}else {
			rent=rentInfoService.findListByHql("from RentInfo where userId=? group by start asc", session.get("id"));
			request.setAttribute("rents", rent);
			return "query";
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
