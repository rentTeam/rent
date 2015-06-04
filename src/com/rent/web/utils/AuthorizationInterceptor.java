package com.rent.web.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 4949812834762901805L;
	public String actionName;


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		boolean flag = false;// 如果有权限访问设置为true；
		// 取得请求的Action名
		actionName = invocation.getInvocationContext().getName();
		// 的名称，在xml中配置的
		String namespace = invocation.getProxy().getNamespace(); // 获取到namespace，还能够获取到要执行的方法，class等
		// System.out.println(namespace+"/user/public");
		if ((namespace != null) && (namespace.trim().length() > 0)) {
			if ("/".equals(namespace.trim())) {
				// 说明是根路径，不需要再增加反斜杠了。
			} else {
				namespace += "/";
			}
		}

//		if (namespace.equals("/user/public/")) {
//			return invocation.invoke();
//		}

        //韦英飘修改
        if (namespace.equals("/home/user/")) {
            return invocation.invoke();
        }

		// TODO 在这里判断用户是否已经登陆,更改此方法，
		// 根据角色来判断用户是否有权限来使用当前的URL（action）
//		if (session == null) {
//            System.out.println("session null");
//			return "login_redir";
//		} else {
//			String role = (String) session.getAttribute("role");
//			// 如果没有登陆，那么就退出系统
//			if (role == null) {
//				// log.debug("please login");
//				return "login_redir";
//			} else {
//				if (namespace.equals("/user/private/")
//						|| namespace.equals("/user/protected/")) {
//					if (role.equals("teacher")||role.equals("student")) {
//						return invocation.invoke();
//					}
//				}
//				if (namespace.equals("/user/admin/")) {
//					// 如果是用户管理界面 则通过
//					if (role.equals("user")) {
//						// log.debug("please login");
//						return invocation.invoke();
//					}
//				}
//			}
//		}

        //登录验证 修改 韦英飘
        if (session == null) {
//            System.out.println("\nAuthorizationInterceptor=> NO SESSION!");
            return "home";
        } else {
            String role = (String) session.getAttribute("role");
            // 如果没有登陆，那么就退出系统
            if (role == null) {
                // log.debug("please login");
//                System.out.println("\nAuthorizationInterceptor=> NO ROLE!");
                return "homeLogin";
            } else {
                if (namespace.startsWith("/user/")) {
                    if (role.equals("nomalUser")||role.equals("rentUser")) {
//                        System.out.println("\nAuthorizationInterceptor=> " + role + " USER");
                        return invocation.invoke();
                    } else {
                        return "homeLogin";
                    }
                }
                if (namespace.startsWith("/admin/")) {
                    // 如果是用户管理界面 则通过
                    if (role.equals("admin")) {
                        // log.debug("please login");
//                        System.out.println("\nAuthorizationInterceptor=> " + role + " USER");
                        return invocation.invoke();
                    } else {
                        return "adminLogin";
                    }
                }
            }
        }

		if (flag) {
			// log.debug("success auth");
			return invocation.invoke();
		} else {
			// 跳转到没有访问权限
//			return "permit_view";
            return "home";
		}

	}
}
