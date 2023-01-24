package id.transferapp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class CustomerServiceHomeControlle {

	@GetMapping
	public String home() {
		return "csHome";
	}
}
