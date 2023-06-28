package com.multi.metahouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.multi.metahouse.common.LoginCheckInterceptor;

//자동으로 구성된 스프링MVC구성을 변경없이 추가작업을 하기 위해 사용
//웹 아닌것 @Configuration 웹은 WebMvcConfigure을 implements해서 사용
//@Configuration Annotation을 선언하고 WebMvcConfigurer을 상속해서 작업
//WebMvcConfigurer에 작업할 수 있는 메소드 제공
// => 인터셉터, 뷰리졸버, 메시지 컨버터, CORS...
@Configuration
public class MyWebConfig implements WebMvcConfigurer{

	//addInterceptors메소드를 이용, interceptors 여러개 이어 붙여 사용할 때 표현 가능(우선순위를 줘서 우선 순위대로 동작함.)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
				.order(1)// -> 1번으로 등록된 인터셉터가 먼저 실행되고, 2번이 실행되도록 순서를 정의
				.addPathPatterns("/**") //인터셉트에 추가할 경로 작성 list로 주거나, string으로 나열가능
				.excludePathPatterns("/login", "/main/index", "/asset/main", "/asset/detail", "/project/main", "/project/detail",
						"/user/profile", "/user/portfolio", "/css/**", "/js/**", "/images/**", "/upload/**");//인터셉트 제외할 경로 작성
	}
	//다음 실행될 인터셉터는 .order에서 순위만 변경해서 그대로 작성하면된다.

	
	//정적리소스의 경로를 설정하는 경우 사용(image, css, js 등의 경로를 변경할 때 사용.)
	//외부경로와 연결할때, 사용한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//특정 path로 요청하는 경우 실제파일이 저장된 위치를 연결해서 리소스를 가져올 수 있도록 처리
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/"); //파일은 /를 3개주고 들어감. 마지막에 반드시 /붙이기
		
	}
	
	
	
}
