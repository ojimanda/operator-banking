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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.InvoicepaketData;
import id.transferapp.entity.PaketData;
import id.transferapp.entity.ProviderCard;
import id.transferapp.service.admin.PaketDataService;
import id.transferapp.service.admin.ProviderCardService;
import id.transferapp.service.operator.InvoicePaketDataService;

@Controller
@RequestMapping("/operator/paketdata")
public class PaketDataController {

	@Autowired
	InvoicePaketDataService invoicePaketDataService;

	@Autowired
	PaketDataService paketDataService;

	@Autowired
	ProviderCardService providerCardService;

	@GetMapping
	public String index(Model model) {
		List<PaketData> listPaket = this.paketDataService.getAllPaketData();
		List<ProviderCard> listProvider = providerCardService.getAllProvider();

		JSONArray parent = new JSONArray();
		try {
			for (PaketData paketData : listPaket) {
				JSONObject child = new JSONObject();
				child.put("provider", paketData.getProviderName().getName());
				child.put("providerId", paketData.getProviderName().getId());
				child.put("name", paketData.getName());
				child.put("price", paketData.getPrice());
				parent.put(child);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		model.addAttribute("listPaket", listPaket);
		model.addAttribute("providers", listProvider);
		model.addAttribute("detailPaket", parent);
		model.addAttribute("paket", new InvoicepaketData());
		return "paketdata";
	}

	// @PostMapping("/save")
	// public @ResponseBody String save(@PathVariable("namaProvider")String
	// namaProvider, @PathVariable("jenisPaket")String jenisPaket,
	// @PathVariable("harga")double harga,
	// @PathVariable("noRek")String noRek, @PathVariable("phoneNum")String phoneNum,
	// RedirectAttributes redirectAttributes) {
	// InvoicepaketData invoice = new InvoicepaketData();
	// invoice.setNamaProvider(namaProvider);
	// invoice.setJenisPaket(jenisPaket);
	// invoice.setPrice(harga);
	// invoice.setNoRek(noRek);
	// invoice.setPhoneNum(phoneNum);
	// this.invoicePaketDataService.save(invoice);
	// return "redirect:/operator/paketdata";
	// }

	@PostMapping
	public String savePaket(@ModelAttribute("paket") InvoicepaketData paketData, RedirectAttributes redirectAttributes,
			BindingResult bindingResult, Model model) {
		List<PaketData> listPaket = this.paketDataService.getAllPaketData();
		List<ProviderCard> listProvider = providerCardService.getAllProvider();
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fail", "Gagal Membeli Paket");
			model.addAttribute("listPaket", listPaket);
			model.addAttribute("providers", listProvider);
			return "paketdata";
		} else {
			invoicePaketDataService.save(paketData);
			redirectAttributes.addFlashAttribute("success", "Paket Berhasil Dibeli");
			return "redirect:/operator/paketdata";
		}

	}
}
