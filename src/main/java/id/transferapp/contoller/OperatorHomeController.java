package id.transferapp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/operator")
public class OperatorHomeController {
	
	@GetMapping
	public String home() {
		return "operatorHome";
	}

}
