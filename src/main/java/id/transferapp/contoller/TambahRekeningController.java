package id.transferapp.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.BankProvider;
import id.transferapp.entity.Nasabah;
import id.transferapp.entity.Rekening;
import id.transferapp.service.admin.BankService;
import id.transferapp.service.cs.RekeningService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cs/rekening")
public class TambahRekeningController {
	
	@Autowired
	RekeningService rekeningService;
	
	@Autowired
	BankService bankService;

	@GetMapping
	public String index(Model model) {
		List<Rekening> listRekening = this.rekeningService.getAll();
		List<BankProvider> bank = this.bankService.getAll();
		model.addAttribute("rekeningForm", new Rekening());
		model.addAttribute("bankForm", new BankProvider());
		model.addAttribute("listBank", bank);
		model.addAttribute("reks", listRekening);
		return "tambahRek";
		
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("rekeningForm") Rekening rekening,  BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		List<Rekening> listRekening = this.rekeningService.getAll();
		if(result.hasErrors()) {
			model.addAttribute("reks", listRekening);
			return "tambahRek";
		}
		else {
			String getRek = rekening.getNoRek();
			Rekening rek = rekeningService.findByNoRek(getRek);
			List<Rekening> list = new ArrayList<>();
			list.add(rek);
			if(list.contains(null)) {
				this.rekeningService.save(rekening);
				redirectAttributes.addFlashAttribute("success", "New Rekening Inserted");
				return "redirect:/cs/rekening";
			}
			else if(list.isEmpty()==false) {
				redirectAttributes.addFlashAttribute("fail", "Rekening Already Exists");
				return "redirect:/cs/rekening";
			}
		}
		return "tambahRek";
		
	}
}
