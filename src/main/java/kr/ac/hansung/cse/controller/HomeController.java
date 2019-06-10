package kr.ac.hansung.cse.controller;

import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//kr.ac.hansung.cse.controller; 패지키의 homecontroller라는 이름의 logger가 만들어진다. -> debug 레벨
	static Logger logger = LoggerFactory.getLogger(HomeController.class);
 
	// 예전에는 @RequestMapping(value="/", method = RequestMethod.GET).사용
	//그런데 귀찮아가지구 이렇게 써도 동일한 기능 구현 가능
	@GetMapping("/")
	public String home(Model model) {

		logger.debug("Calling home( )");

		//모델에 message라는 키값으로 hello world 단어를 넣음
		model.addAttribute("message", "hello world");
		return "index";

	}
}
