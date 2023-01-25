package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.transferapp.entity.TiketPesawat;
import id.transferapp.service.admin.TiketPesawatService;
import id.transferapp.service.operator.InvoiceTiketPesawatService;

@Controller
@RequestMapping("/operator/tiketpesawat")
public class TiketPesawatController {
	
	@Autowired
	InvoiceTiketPesawatService invoiceTiketPesawatService;
	
	@Autowired
	TiketPesawatService tiketPesawatService;
	
	@GetMapping
	public String index(Model model) {
		List<TiketPesawat> listTiket = this.tiketPesawatService.getAllTiketPesawat();
		model.addAttribute("tiket", listTiket);
		return "tiketpesawat";
	}

}
