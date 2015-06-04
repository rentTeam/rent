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
		boolean flag = false;// �����Ȩ�޷�������Ϊtrue��
		// ȡ�������Action��
		actionName = invocation.getInvocationContext().getName();
		// �����ƣ���xml�����õ�
		String namespace = invocation.getProxy().getNamespace(); // ��ȡ��namespace�����ܹ���ȡ��Ҫִ�еķ�����class��
		// System.out.println(namespace+"/user/public");
		if ((namespace != null) && (namespace.trim().length() > 0)) {
			if ("/".equals(namespace.trim())) {
				// ˵���Ǹ�·��������Ҫ�����ӷ�б���ˡ�
			} else {
				namespace += "/";
			}
		}

//		if (namespace.equals("/user/public/")) {
//			return invocation.invoke();
//		}

        //ΤӢƮ�޸�
        if (namespace.equals("/home/user/")) {
            return invocation.invoke();
        }

		// TODO �������ж��û��Ƿ��Ѿ���½,���Ĵ˷�����
		// ���ݽ�ɫ���ж��û��Ƿ���Ȩ����ʹ�õ�ǰ��URL��action��
//		if (session == null) {
//            System.out.println("session null");
//			return "login_redir";
//		} else {
//			String role = (String) session.getAttribute("role");
//			// ���û�е�½����ô���˳�ϵͳ
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
//					// ������û�������� ��ͨ��
//					if (role.equals("user")) {
//						// log.debug("please login");
//						return invocation.invoke();
//					}
//				}
//			}
//		}

        //��¼��֤ �޸� ΤӢƮ
        if (session == null) {
//            System.out.println("\nAuthorizationInterceptor=> NO SESSION!");
            return "homeLogin";
        } else {
            String role = (String) session.getAttribute("role");
            // ���û�е�½����ô���˳�ϵͳ
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
                    // ������û�������� ��ͨ��
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
			// ��ת��û�з���Ȩ��
//			return "permit_view";
            return "home";
		}

	}
}
