package com.rent.web.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthFilter implements Filter{
	private static Log log = LogFactory.getLog(AuthFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		if (log.isDebugEnabled()) {
			// log.debug("初始化权限过滤器。");
		}
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		/**
		 * 1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括
		 * 表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过
		 * 滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
		 */
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		/**
		 * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
		 * 无法得到的方法，就要把此request对象构造成HttpServletRequest
		 */
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:
		if ((currentURL.length() == currentURL.replace(
				"register.jsp", "").length() || currentURL.length() == currentURL
				.replace("login.jsp", "").length())
				&& currentURL.indexOf(".jsp") > -1) {
			// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
				response.sendRedirect(request.getContextPath()
						+ "/user/public/user_loginView.do");
		}
		// 加入filter链继续向下执行

		filterChain.doFilter(request, response);

		/**
		 * 调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作 为它
		 * 的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另
		 * 一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
		 */
	}

	public void destroy() {
	}
}
