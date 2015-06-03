package com.rent.web.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Car;
import com.rent.domin.Manage;
import com.rent.domin.Picture;
import com.rent.domin.PictureCar;
import com.rent.domin.PictureManager;
import com.rent.domin.PictureUser;
import com.rent.domin.User;
import com.rent.service.CarService;
import com.rent.service.ManagerService;
import com.rent.service.PictureCarService;
import com.rent.service.PictureManagerService;
import com.rent.service.PictureService;
import com.rent.service.PictureUserService;
import com.rent.service.UserService;
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
	
	@Resource
	private CarService carService;
	
	@Resource
	private ManagerService managerService;
	
	@Resource
	private UserService userService;
	
	private File img[];
	private String imgFileName[];
	
	public File[] getImg() {
		return img;
	}
	public void setImg(File[] img) {
		this.img = img;
	}
	public String[] getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String[] imgFileName) {
		this.imgFileName = imgFileName;
	}
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
	 * ��ͼƬ�ϴ�ҳ��
	 * @return
	 */
	public String intoUpload(){
		return "intoUpload";
	}
	
	/**
	 * ������ͼƬ����ҳ
	 * @return
	 */
	public String intoModify(){
		String hql="from Picture where id is in("
				+ "select pictureId form PictureCar where carId=?)";
		List<Picture> pictures=pictureService.findListByHql(hql, request.getParameter("carId"));
		for(Picture p:pictures){
			pictureService.delete(p);
		}
		return "intoModify";
	}
	/**
	 * ����ԱͼƬ�ϴ�ҳ��
	 * @return
	 */
	public String intoUpload2(){
		return "intoUpload2";
	}
	/**
	 *��ͼƬ�ϴ�
	 * @return
	 */
	public String imgUpload(){
		Picture picture =new Picture();
        OutputStream outputStream=null;
        InputStream inputStream=null;
		try{
			String reqlpath=ServletActionContext.getServletContext().getRealPath("/carPicture");
            File saveFile=new File(reqlpath);
            Car car=carService.getEntity(Car.class, request.getParameter("carId"));
            if(!saveFile.exists())
                saveFile.mkdirs();
            for(int i=0;i<img.length;i++){
            	//�ļ������� ��UUID
                String extension01=img[i].getName().replace(img[i].getName().substring(0,img[i].getName().lastIndexOf(".")),UUID.randomUUID().toString());
                //;
                outputStream=new FileOutputStream(reqlpath+"/"+extension01);
                byte[] bs=new byte[1024];
                inputStream=new FileInputStream(img[i]);
                int length=0;
                while((length=inputStream.read(bs))>0){
            		outputStream.write(bs, 0, length);
            	}
                picture.setFileFileName(extension01);
                picture.setType(model.getType());
                picture.setUrl(reqlpath+"\\"+extension01);
                pictureService.save(picture);
                PictureCar pictureCar=new PictureCar();
                pictureCar.setCarId(car);
                pictureCar.setPictureId(picture);
                pictureCarService.save(pictureCar);
            }  
            inputStream.close();
            outputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * ����ԱͼƬ�ϴ�
	 * @return
	 */
	public String imgUploads(){
		Picture picture =new Picture();
        OutputStream outputStream=null;
        InputStream inputStream=null;
		try{
			String reqlpath=ServletActionContext.getServletContext().getRealPath("/managerPicture");
            File saveFile=new File(reqlpath);
            Manage manager=managerService.getEntity(Manage.class,request.getParameter("managerId"));
            if(!saveFile.exists())
                saveFile.mkdirs();
            for(int i=0;i<img.length;i++){
            	//�ļ������� ��UUID
                String extension01=img[i].getName().replace(img[i].getName().substring(0,img[i].getName().lastIndexOf(".")),UUID.randomUUID().toString());
                //;
                outputStream=new FileOutputStream(reqlpath+"/"+extension01);
                byte[] bs=new byte[1024];
                inputStream=new FileInputStream(img[i]);
                int length=0;
                while((length=inputStream.read(bs))>0){
            		outputStream.write(bs, 0, length);
            	}
                picture.setFileFileName(extension01);
                picture.setType(model.getType());
                picture.setUrl(reqlpath+"\\"+extension01);
                pictureService.save(picture);
                PictureManager manager2=new PictureManager();
                manager2.setManagerId(manager);
                manager2.setPictureId(picture);
                pictureManagerService.save(manager2);
            }  
            inputStream.close();
            outputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * �鿴�û���ͼƬ
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
		User user=userService.getEntity(User.class, request.getParameter("userId"));
		request.setAttribute("identity", identity);
		request.setAttribute("student", student);
		request.setAttribute("other", other);
		request.setAttribute("user", user);
		return "queryUser";
	}
	
	/**
	 * �鿴����Ա����Ƭ(*)
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
	 * �鿴����ͼƬ(*)
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
	 * ����ͼƬidɾ��ͼƬ
	 * @return
	 */
	public String delete1(){
		String hql="from Picture where id =?";
		List<Picture> userPicture=pictureService.findListByHql(hql, model.getId());
		if(!userPicture.isEmpty()){
			String url=userPicture.get(0).getUrl();
			pictureService.delete(userPicture.get(0));
			File file=new File(url);
			file.delete();
			ajaxReturn("ok", "�ɹ�ɾ��", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "ɾ��ʧ��", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * ɾ������ԱͼƬ
	 * @return
	 */
	public String delete2(){
		String hql="from Picture where id is in("
				+ "select pictureId from PictureManager where managerId=?";
		List<Picture> managerPicture=pictureService.findListByHql(hql, model.getId());
		if(!managerPicture.isEmpty()){
			for(Picture p:managerPicture){
				String url=p.getUrl();
				pictureService.delete(p);
				File file=new File(url);
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
	 * ɾ������ͼƬ
	 * @return
	 */
	public String delete3(){
		String hql="from Picture where id is in("
				+ "select pictureId from PictureCar where carId=?";
		List<Picture> carPicture=pictureService.findListByHql(hql, model.getId());
		if(!carPicture.isEmpty()){
			for(Picture p:carPicture){
				String url=p.getUrl();
				pictureService.delete(p);
				File file=new File(url);
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
	 * ɾ���û���ͼƬ
	 * @return
	 */
	public String delete4(){
		String hql="from Picture where id is in("
				+ "select pictureId from PictureUser where userId=?";
		List<Picture> userPicture=pictureService.findListByHql(hql, model.getId());
		if(!userPicture.isEmpty()){
			for(Picture p:userPicture){
				String url=p.getUrl();
				pictureService.delete(p);
				File file=new File(url);
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
