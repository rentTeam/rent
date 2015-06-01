package com.rent.web.action.home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Picture;
import com.rent.domin.PictureUser;
import com.rent.domin.User;
import com.rent.service.PictureService;
import com.rent.service.PictureUserService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Home-PictureAction")
@Scope("prototype")
public class PictureAction extends BaseAction<Picture> implements SessionAware{
	
private Map<String, Object> session = null;
	
	//ajax json数据变量
	private Map<String, Object> jsonData;
	
	@Resource
	private UserService userService;
	
	@Resource
	private PictureService pictureService;
	
	@Resource
	private PictureUserService pictureUserService;
	
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
	
	/**
	 * 跳到身份证图片上传页
	 * @return
	 */
	public String identityUpload(){
		return "identityUpload";
	}
	
	/**
	 * 跳到学生证图片上传页
	 * @return
	 */
	public String studentUpload(){
		return "studentUpload";
	}
	
	/**
	 * 跳到其他图片上传页
	 * @return
	 */
	public String otherUpload(){
		return "otherUpload";
	}
	/**
	 * 图片上传
	 * @return
	 * @throws IOException 
	 */
	public String imgUpload(){
		Picture picture =new Picture();
        OutputStream outputStream=null;
        InputStream inputStream=null;
		try{
			String reqlpath=ServletActionContext.getServletContext().getRealPath("/my");
            File saveFile=new File(reqlpath);
            User user=userService.getEntity(User.class,request.getParameter("userId"));
            if(!saveFile.exists())
                saveFile.mkdirs();
            for(int i=0;i<img.length;i++){
            	//文件重命名 用UUID
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
                PictureUser pictureUser=new PictureUser();
                pictureUser.setPictureId(picture);
                pictureUser.setUserId(user);
                pictureUserService.save(pictureUser);
            }  
            inputStream.close();
            outputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
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
