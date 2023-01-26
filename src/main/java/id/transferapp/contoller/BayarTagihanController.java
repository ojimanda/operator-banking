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

import id.transferapp.entity.InvoiceTagihan;
import id.transferapp.entity.InvoicepaketData;
import id.transferapp.entity.PaketData;
import id.transferapp.entity.ProviderCard;
import id.transferapp.entity.Tagihan;
import id.transferapp.service.admin.TagihanService;
import id.transferapp.service.operator.InvoiceTagihanService;

@Controller
@RequestMapping("/operator/bayartagihan")
public class BayarTagihanController {
	
	@Autowired
	InvoiceTagihanService invoiceTagihanService;
	
	@Autowired
	TagihanService tagihanService;

	@GetMapping
	public String index(Model model) {
		List<Tagihan> listTagihan = this.tagihanService.getAllTagihan();
		
		JSONArray parent = new JSONArray();
        try {
        	for(Tagihan tagihan : listTagihan) {
        		JSONObject child = new JSONObject();
        		child.put("name", tagihan.getJenisTagihan());
        		child.put("noTagihan", tagihan.getNoTagihan());
        		child.put("fee", tagihan.getFee());
        		parent.put(child);
        	}
        }catch (Exception e) {
			// TODO: handle exception
		}
        model.addAttribute("detailTagihan", parent);
        model.addAttribute("tagihan", new InvoiceTagihan());
		return "listrikinternet";
	}
	
	@PostMapping
	public String savePaket(@ModelAttribute("tagihan") InvoiceTagihan invoiceTagihan, RedirectAttributes redirectAttributes,
			BindingResult bindingResult, Model model) {
		List<Tagihan> listTagihan = this.tagihanService.getAllTagihan();
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fail", "Gagal Membayar Tagihan");
			return "paketdata";
		} else {
			invoiceTagihanService.save(invoiceTagihan);
			redirectAttributes.addFlashAttribute("success", "Tagihan Berhasil Dibayar");
			return "redirect:/operator/bayartagihan";
		}
	}
}
