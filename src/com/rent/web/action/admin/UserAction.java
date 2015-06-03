package com.rent.web.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rent.domin.Picture;
import com.rent.domin.RoleInfo;
import com.rent.domin.User;
import com.rent.service.PictureService;
import com.rent.service.RoleService;
import com.rent.service.UserService;
import com.rent.web.action.BaseAction;

@Controller("Admin-UserAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> implements SessionAware{
	
	private Map<String, Object> session = null;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PictureService pictureService;
	
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
	 * 查看所有用户的基本信息
	 * @return
	 */
	public String query(){
		List<User> ulist=new ArrayList<User>();
		List<RoleInfo> rlist=new ArrayList<RoleInfo>();
		String hql="from User as user";
		int start=0;
		int limit=10;
		String[] params=null;
		if(null!=request.getParameter("start"))
			start=Integer.parseInt(request.getParameter("start"));
		if(null!=request.getParameter("limit"))
			limit=Integer.parseInt(request.getParameter("limit"));
		ulist=userService.queryForPages(hql, params, start, limit);
		for(User u:ulist){
			RoleInfo role=roleService.getEntity(RoleInfo.class, u.getRoleId().toString());
			rlist.add(role);
		}
		request.setAttribute("ulist", ulist);
		request.setAttribute("rlist", rlist);
		return "query";
	}
	
	/**
	 * 根据角色查询用户
	 * @return
	 */
	public String queryByRole(){
		List<User> ulist=new ArrayList<User>();
		String hql="from User where roleId=?";
		int start=0;
		int limit=10;
		String[] params=new String[1];
		params[0]=request.getParameter("roleId");
		if(null!=request.getParameter("start"))
			start=Integer.parseInt(request.getParameter("start"));
		if(null!=request.getParameter("limit"))
			limit=Integer.parseInt(request.getParameter("limit"));
		ulist=userService.queryForPages(hql, params, start, limit);
		request.setAttribute("ulist", ulist);
		return "queryByRole";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String delete(){
		String userId=request.getParameter("userId");
		User user=userService.getEntity(User.class, userId);
		List<Picture> plist=new ArrayList<Picture>();
		String hql="from Picture where id is in("
				+ "select pictureId from PictureUser where userId=?";
		plist=pictureService.findListByHql(hql, userId);
		if(userService.delete(user)){
			for(Picture p:plist){
				String url=p.getUrl();
				pictureService.delete(p);
				File file =new File(url);
				file.delete();
			}
			ajaxReturn("ok", "成功删除", "ok");
			return "jsonReturn";
		}else{
			ajaxReturn("error", "删除失败", "error");
			return "jsonReturn";
		}
	}
	
	/**
	 * 转到发邮件的页面
	 * @return
	 */
	public String intoSentEmial(){
		return "intoSentEmial";
	}
	/**
     * 发送邮件用
     * @return
     */
    public String sentInfo(){
    	String content=request.getParameter("conent");
    	String subject=request.getParameter("subject");
    	String sendMail=request.getParameter("sendMail");
    	String password=request.getParameter("password");
    	String receiveMail=request.getParameter("recevieMail");
    	String mailHost=request.getParameter("mailHost");
    	String i=request.getParameter("port");
    	Integer port=Integer.valueOf(i);
    	try{
    		sendMailForText(content, subject, sendMail, password, receiveMail, mailHost, port);
    	}catch(Exception e){
    		e.printStackTrace();
    		this.ajaxReturn("error", "发送失败", "error");
			return "jsonReturn";
    	}
    	this.ajaxReturn("ok", "发送成功", "ok");
		return "jsonReturn";
    }
    
    /**
     * 发送邮件
     * @param content 邮件内容
     * @param subject 主题
     * @param sendMail 发送地址
     * @param password 发送邮件的登录密码
     * @param receiveMail 接收邮件的地址,可以包含多个地址,以";"隔开
     * @param mailHost 发送邮件的smtp服务器
     * @param port 端口号
     */
    public final void sendMailForText(String content,String subject,final String sendMail,
    		final String password,String receiveMail,String mailHost,int port){
    	Properties props=new Properties();
    	props.setProperty("mail.smtp.auth", "true");
    	props.setProperty("mail.transport.protocol", "smtp");
    	props.setProperty("mail.host",mailHost);
    	props.setProperty("mail.smtp.port", String.valueOf(port));
    	try{
    		Session session=Session.getInstance(props,new Authenticator(){
    			protected PasswordAuthentication getPasswordAuthentication(){
    				return new PasswordAuthentication(sendMail,password); 
    			}
    		});
    		session.setDebug(true);
    		Message msg = new MimeMessage(session);
    		msg.setFrom(new InternetAddress(sendMail)); 
    		msg.setSubject(subject);
    		Address[] tos = null;//邮件群发功能,添加多个接受地址,有些邮件服务器可能会屏蔽 
    		if(receiveMail!=null&&"".equals(receiveMail)){
    			String[] recevers=receiveMail.split(";");
    			if(recevers!=null){
    				//为每个邮件接收者创建一个地址
    				tos=new InternetAddress[recevers.length];
    				for(int i=0;i<recevers.length;i++){
    					tos[i]=new InternetAddress(recevers[i]);
    				}
    			}
    		}
    		msg.setRecipients(RecipientType.TO, InternetAddress.parse(receiveMail));//一个地址时
    		msg.setRecipients(RecipientType.TO, tos);
    		msg.setText(content);
    		Transport.send(msg); 
    	}catch(Exception e){
    		e.printStackTrace();
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
