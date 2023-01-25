package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.InvoicepaketData;
import id.transferapp.entity.PaketData;
import id.transferapp.service.admin.PaketDataService;
import id.transferapp.service.operator.InvoicePaketDataService;


@Controller
@RequestMapping("/operator/paketdata")
public class PaketDataController {
	
	@Autowired
	InvoicePaketDataService invoicePaketDataService;
	
	@Autowired
	PaketDataService paketDataService;

	@GetMapping
	public String index(Model model) {
		List<PaketData> listPaket = this.paketDataService.getAllPaketData();
		model.addAttribute("paket", listPaket);
		return "paketdata";
	}
	
	@PostMapping("/save")
	public @ResponseBody String save(@PathVariable("namaProvider")String namaProvider, @PathVariable("jenisPaket")String jenisPaket, @PathVariable("harga")double harga,
			@PathVariable("noRek")String noRek, @PathVariable("phoneNum")String phoneNum, RedirectAttributes redirectAttributes) {
		InvoicepaketData invoice = new InvoicepaketData();
		invoice.setNamaProvider(namaProvider);
		invoice.setJenisPaket(jenisPaket);
		invoice.setPrice(harga);
		invoice.setNoRek(noRek);
		invoice.setPhoneNum(phoneNum);
		this.invoicePaketDataService.save(invoice);
		return "redirect:/operator/paketdata";
	}
}
