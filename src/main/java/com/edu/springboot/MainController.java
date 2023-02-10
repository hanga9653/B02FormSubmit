package com.edu.springboot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	//컨텍스트 루트 경로에 대한 매핑
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//JSP를 뷰로 사용하기 위한 매핑
	@RequestMapping("/index.do")
	public String index() {
		//뷰의 경로 반환
		return "index";
	}
	
	@RequestMapping("/sub.do")
	public String sub() {
		return "sub/sub";
	}
	
	/*
	1. request내장객체를 통해 폼값을 전송받아 사용
	 */
	@RequestMapping("/form1.do")
	public String form1(HttpServletRequest httpServletRequest,
			Model model) {
		
		//폼값을 받아 변수에 저장
		String name = httpServletRequest.getParameter("name");
		String age = httpServletRequest.getParameter("age");
		
		//모델에 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		//뷰의 경로를 반환
		return "form/submit1";
	}
	
	/*
	2. @RequestParam 어노테이션으로 통해 파라미터를 받은 후 변수에 저장
	 */
	@RequestMapping("/form2.do")
	public String form2(@RequestParam("name") String name,
			@RequestParam("age") String age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit2";
	}
	
	/*
	3. 커맨드객체를 통해 파라미터를 한꺼번에 받아 DTO에 저장한 후 모델에 저장까지 해준다.
		파라미터를 저장한 DTO를 모델에 저장하므로 View에서는 getter를 통해 출력해야한다.
	 */
	@RequestMapping("/form3.do")
	public String form3(PersonDTO personDTO, Model model) {
		
		return "form/submit3";
	}
	
	/*
	4. @PathVariable 어노테이션 경로형태로 전달되는 값을 변수로 사용한다. 단 이경우 
		파라미터를 경로명으로 인식하므로 정적리소스 사용시 경로설정에 주의해야한다.
	 */
	@RequestMapping("/form4/{name}/{age}")
	public String form4(@PathVariable String name,
			@PathVariable String age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit4";
	}
	
}
