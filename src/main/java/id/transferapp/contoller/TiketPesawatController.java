package id.transferapp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tiketpesawat")
public class TiketPesawatController {
	
	@GetMapping
	public String index() {
		return "tiketpesawat";
	}

}
