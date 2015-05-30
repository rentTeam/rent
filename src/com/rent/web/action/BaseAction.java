package com.rent.web.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -1314560516485837572L;

	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	public T model;

	public BaseAction() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	/**
	 * 将对象放入对象栈
	 * 
	 * @param object
	 */
	public void putObjectToValueStack(Object object) {
		ActionContext.getContext().getValueStack().push(object);
	}

	/**
	 * 将对象放入Map�?
	 * 
	 * @param key
	 * @param value
	 */
	public void putObjectToMapStack(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

}
