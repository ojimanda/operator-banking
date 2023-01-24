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
import id.transferapp.service.admin.BankService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/bank")
public class BankProviderController {
	
	@Autowired
	BankService bankService;
	
	@GetMapping
	public String index(Model model) {
		List<BankProvider> listBank = this.bankService.getAll();
		model.addAttribute("bankProviderForm", new BankProvider());
		model.addAttribute("banks", listBank);
		return "bankProvider";
		
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("bankProviderForm") BankProvider bankProvider, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		List<BankProvider> listBank = this.bankService.getAll();
		if(result.hasErrors()) {
			model.addAttribute("banks", listBank);
			return "bankProvider";
		}
		else {
			String getName = bankProvider.getBankname().toUpperCase();
			BankProvider bank = bankService.getBankByName(getName);
			List<BankProvider> list = new ArrayList<>();
			list.add(bank);
			if(list.contains(null)) {
				this.bankService.save(bankProvider);
				redirectAttributes.addFlashAttribute("success", "Bank Provider Inserted");
				return "redirect:/admin/bank";
			}
			else{
				redirectAttributes.addFlashAttribute("fail", "Bank Provider Already Exists");
				return "redirect:/admin/bank";
			}
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		Optional<BankProvider> bank = this.bankService.getBankById(id);
		if(bank.isPresent()) {
			this.bankService.delete(bank.get());
		}
		redirectAttributes.addFlashAttribute("success","Succesfully Delete " + bank.get().getBankname());
		return "redirect:/admin/bank";
	}

}
