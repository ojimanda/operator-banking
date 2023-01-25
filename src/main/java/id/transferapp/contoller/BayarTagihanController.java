package id.transferapp.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.transferapp.service.operator.InvoiceTagihanService;

@Controller
@RequestMapping("/operator/bayartagihan")
public class BayarTagihanController {
	
	@Autowired
	InvoiceTagihanService invoiceTagihanService;

	@GetMapping
	public String index() {
		return "listrikinternet";
	}
}
