package id.transferapp.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Controller
@RequestMapping("/paketdata")
public class PaketDataController {

	@GetMapping
	public String index() {
		return "paketdata";
	}
	
}
