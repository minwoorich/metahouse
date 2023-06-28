package com.multi.metahouse.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.multi.metahouse.domain.entity.user.User;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인 사용자인 경우 세션에 user라는 이름의 어트리뷰트가 저장되어 있으므로
		//user가 없으면 처리되지 않음을 의미
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User)session.getAttribute("user");
			if(user==null) { //로그인하지 않은 상태
				response.sendRedirect("/metahaus/login");
				//로그인하지 않은 사용자는 다음(뷰에서 선택한 path로 이동되지 않도록)으로 넘어가지 않도록 false를 리턴
				return false;
			}
		}
		//로그인된 사용자는 다음으로 넘어갈 수 있도록 true 리턴
		return true;
	}
	
}
