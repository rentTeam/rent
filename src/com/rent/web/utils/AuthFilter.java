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
			// log.debug("��ʼ��Ȩ�޹�������");
		}
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		/**
		 * 1,doFilter�����ĵ�һ������ΪServletRequest���󡣴˶�����������ṩ�˶Խ������Ϣ������
		 * �����ݡ�cookie��HTTP����ͷ������ȫ���ʡ��ڶ�������ΪServletResponse��ͨ���ڼ򵥵Ĺ�
		 * �����к��Դ˲��������һ������ΪFilterChain���˲�����������servlet��JSPҳ��
		 */
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		/**
		 * �������HTTP���󣬲�����Ҫ��������getHeader��getCookies����ServletRequest��
		 * �޷��õ��ķ�������Ҫ�Ѵ�request�������HttpServletRequest
		 */
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI(); // ȡ�ø�Ŀ¼����Ӧ�ľ���·��:
		if ((currentURL.length() == currentURL.replace(
				"register.jsp", "").length() || currentURL.length() == currentURL
				.replace("login.jsp", "").length())
				&& currentURL.indexOf(".jsp") > -1) {
			// �жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ��ҳ�棬����ǾͲ���session���жϣ���ֹ������ѭ��
				response.sendRedirect(request.getContextPath()
						+ "/user/public/user_loginView.do");
		}
		// ����filter����������ִ��

		filterChain.doFilter(request, response);

		/**
		 * ����FilterChain�����doFilter������Filter�ӿڵ�doFilter����ȡһ��FilterChain������ Ϊ��
		 * ��һ���������ڵ��ô˶����doFilter����ʱ��������һ����صĹ����������û����
		 * һ����������servlet��JSPҳ���������servlet��JSPҳ�汻���
		 */
	}

	public void destroy() {
	}
}
