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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.Rekening;
import id.transferapp.entity.Transfer;
import id.transferapp.service.admin.BankService;
import id.transferapp.service.cs.RekeningService;
import id.transferapp.service.operator.TransferService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/operator/transfer")
public class TransferController {
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	RekeningService rekeningService;
	
	@Autowired
	BankService bankService;

	@GetMapping
	public String index(Model model) {
		List<Transfer> listTransfer = this.transferService.getAll();
		model.addAttribute("transferForm", new Transfer());
		model.addAttribute("transfer", listTransfer);
		return "operatorTransaction";
		
	}
	
	@PostMapping("/checkRekening/{noRek}")
	public @ResponseBody List<String> rekening(@PathVariable("noRek") String noRek, Model model) {
		List<Transfer> listTransfer = this.transferService.getAll();
		model.addAttribute("transferForm", new Transfer());
		model.addAttribute("transfer", listTransfer);
		List<String> list = new ArrayList<>();
		Rekening rek = rekeningService.findByNoRek(noRek);
		if(rek.equals(null)) {
			return list;
		}
		String norek = rek.getNoRek();
		String nama = rek.getNasabah().getNamaLengkap();
		String noId = rek.getNasabah().getNoIdentitas();
		String email = rek.getNasabah().getEmail();
		String contact = rek.getNasabah().getPhoneNum();
		list.add(0, norek);
		list.add(1, nama);
		list.add(2, noId);
		list.add(3, email);
		list.add(4, contact);
		model.addAttribute(list);
		model.addAttribute("transferForm", listTransfer);
		model.addAttribute("transfer", listTransfer);
		return list;
		}	
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("transferForm") Transfer transfer,  BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		Transfer tran = transfer;
		List<Transfer> listTransfer = this.transferService.getAll();
		List<Rekening> rek1 = new ArrayList<>();
		List<Rekening> rek2 = new ArrayList<>();
		if(result.hasErrors()) {
			model.addAttribute("transfer", listTransfer);
			return "operatorTransaction";
		}
		else {
			Rekening rekeningPengirim = rekeningService.findByNoRek(tran.getRekPengirim().getNoRek());
			Rekening rekeningPenerima = rekeningService.findByNoRek(tran.getRekPenerima().getNoRek());
			rek1.add(rekeningPengirim);
			rek2.add(rekeningPenerima);
			
			if(rek1.contains(null) || rek2.contains(null)) {
				model.addAttribute("transfer", listTransfer);
				redirectAttributes.addFlashAttribute("fail", "Rekening Pengirim atau Penerima tidak ada");
				return "redirect:/operator/transfer";
			}
			else if(rek1.isEmpty() == false && rek2.isEmpty()== false) {
				Long idPengirim = rekeningPengirim.getId();
				Long idPenerima = rekeningPenerima.getId();
				Optional<Rekening> pengirim = this.rekeningService.getRekById(idPengirim);
				Optional<Rekening> penerima = this.rekeningService.getRekById(idPenerima);
				Rekening rekPengirim = pengirim.get();
				Rekening rekPenerima = penerima.get();
				String bankAsal = rekPengirim.getBank().getBankname();
				String bankTuju = rekPenerima.getBank().getBankname();
				double amount = tran.getAmount();
				double sisa;
				if(bankAsal.equals(bankTuju)) {
					sisa = rekPengirim.getSaldo() - amount;
					if(sisa < 50000) {
						model.addAttribute("transfer", listTransfer);
						redirectAttributes.addFlashAttribute("fail", "Saldo kurang dari 50000");
						return "redirect:/operator/transfer";
					}
					else {
						this.transferService.save(transfer);
						redirectAttributes.addFlashAttribute("success", "Transfer Success");
						return "redirect:/operator/transfer";
					}
				}else if(bankAsal != bankTuju){
					double fee = 6500;
					sisa = rekPengirim.getSaldo() - (amount + fee);
					if(sisa < 50000) {
						model.addAttribute("transfer", listTransfer);
						redirectAttributes.addFlashAttribute("fail", "Saldo kurang dari 50000");
						return "redirect:/operator/transfer";
					}
					else {
						this.transferService.save(transfer);
						redirectAttributes.addFlashAttribute("success", "Transfer Success");
						return "redirect:/operator/transfer";
					}
				}

			}
		}
		return "operatorTransaction";
	}
}
