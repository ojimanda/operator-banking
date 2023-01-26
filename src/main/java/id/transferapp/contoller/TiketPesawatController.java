package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.InvoiceTiketPesawat;
import id.transferapp.entity.InvoicepaketData;
import id.transferapp.entity.PaketData;
import id.transferapp.entity.ProviderCard;
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
		
		JSONArray parent = new JSONArray();
        try {
        	for(TiketPesawat tiketPesawat : listTiket) {
        		JSONObject child = new JSONObject();
        		child.put("name", tiketPesawat.getName());
        		child.put("price", tiketPesawat.getPrice());
        		parent.put(child);
        	}
        }catch (Exception e) {
			// TODO: handle exception
		}
        model.addAttribute("detailTiket", parent);
        model.addAttribute("tikets", listTiket);
        model.addAttribute("tiket", new InvoiceTiketPesawat());
		return "tiketpesawat";
	}
	
	@PostMapping
	public String saveTiket(@ModelAttribute("tiket") InvoiceTiketPesawat tiketPesawat, RedirectAttributes redirectAttributes,
			BindingResult bindingResult, Model model) {
		List<TiketPesawat> listTiket = this.tiketPesawatService.getAllTiketPesawat();
		if (bindingResult.hasErrors()) {
			model.addAttribute("tiket", listTiket);
			redirectAttributes.addFlashAttribute("fail", "Gagal Membeli Tiket");
			return "tiketpesawat";
		} else {
			invoiceTiketPesawatService.save(tiketPesawat);
			redirectAttributes.addFlashAttribute("success", "Tiket Berhasil Dibeli");
			return "redirect:/operator/tiketpesawat";
		}

	}

}
