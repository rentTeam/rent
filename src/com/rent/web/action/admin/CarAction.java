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
import com.rent.domin.Picture;
import com.rent.service.CarService;
import com.rent.service.PictureService;
import com.rent.web.action.BaseAction;

@Controller("Admin-CarAction")
@Scope("prototype")
public class CarAction extends BaseAction<Car> implements SessionAware{
	private Map<String, Object> session = null;
	
	@Resource
	private CarService carService;
	
	@Resource
	private PictureService pictureService;
	
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
	 * ��ȡ���������
	 * @return
	 */
	private String getMaxId(){
		String hql="from Car group by carId having Max(carId)";
		List<Car> clist=carService.findListByHql(hql);
		if(clist.isEmpty()){
			return "car1001";
		}else{
			String id=clist.get(0).getCarId();//��ȡ���ĳ����
			String subId=id.substring(3);//��ȡ��ŵĺ�4λ
			return "car"+String.valueOf(Integer.parseInt(subId)+1);
		}
	}
	
	/**
	 * �鿴���г�����Ϣ
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
	
	public String intoAdd(){
		return "intoAdd";
	}
	/**
	 * ��ӳ�����Ϣ
	 * @return
	 */
	public String add(){
		Car car=new Car();
		car.setCarId(getMaxId());
		car.setModel(model.getModel());
		car.setPrice(model.getPrice());
		car.setTimeLimit(model.getTimeLimit());
		car.setTitle(model.getTitle());
		car.setType(model.getType());
		if(carService.save(car)){
			ajaxReturn("ok", "�ɹ����", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "���ʧ��", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ɾ��������Ϣ
	 * @return
	 */
	public String delete(){
		String carId=request.getParameter("carId");
		Car car=carService.getEntity(Car.class, carId);
		List<Picture> plist=new ArrayList<Picture>();
		String hql="from Picture where id is in("
				+ "select pictureId from PictureCar where carId=?";
		plist=pictureService.findListByHql(hql, carId);
		if(carService.delete(car)){
			for(Picture p:plist){
				String url=p.getUrl();
				pictureService.delete(p);
				File file =new File(url);
				file.delete();
			}
			ajaxReturn("ok", "�ɹ�ɾ��", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "ɾ��ʧ��", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ת��������Ϣ����ҳ
	 * @return
	 */
	public String intoUpdate(){
		Car car=carService.getEntity(Car.class, model.getCarId());
		request.setAttribute("car", car);
		return "intoUpdate";
	}
	/**
	 * ���³�����Ϣ
	 * @return
	 */
	public String update(){
		Car car=carService.getEntity(Car.class, model.getCarId());
		car.setModel(model.getModel());
		car.setPrice(model.getPrice());
		car.setTimeLimit(model.getTimeLimit());
		car.setTitle(model.getTitle());
		car.setType(model.getType());
		if(carService.update(car)){
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
