package id.transferapp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listrikinternet")
public class ListrikInternetController {

	@GetMapping
	public String index() {
		return "listrikinternet";
	}
}
