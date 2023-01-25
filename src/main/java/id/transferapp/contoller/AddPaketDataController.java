package id.transferapp.contoller;

import id.transferapp.entity.PaketData;
import id.transferapp.entity.ProviderCard;
import id.transferapp.entity.TiketPesawat;
import id.transferapp.service.admin.PaketDataService;
import id.transferapp.service.admin.ProviderCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/paketdata")
public class AddPaketDataController {

    @Autowired
    PaketDataService paketDataService;

    @Autowired
    ProviderCardService providerCardService;

    @GetMapping
    public String homeAddPaket(Model model) {
        List<PaketData> paketDataList = paketDataService.getAllPaketData();
        List<ProviderCard> providerCards = providerCardService.getAllProvider();
        model.addAttribute("listProvider", providerCards);
        model.addAttribute("listPaketData", paketDataList);
        model.addAttribute("paketdata", new PaketData());
        model.addAttribute("providercard", new ProviderCard());
        return "addPaketData";
    }

    @PostMapping("/provider")
    public String addProvider(@ModelAttribute("providercard") ProviderCard providerCard, BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        List<PaketData> paketDataList = paketDataService.getAllPaketData();
        if (result.hasErrors()) {
            model.addAttribute("listPaketData", paketDataList);
            return "addPaketData";
        } else {
            providerCardService.saveProvider(providerCard);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/admin/paketdata";
        }

    }

    @PostMapping
    public String addTiket(@ModelAttribute("paketdata") PaketData paketData, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        List<PaketData> paketDataList = paketDataService.getAllPaketData();
        if (result.hasErrors()) {
            model.addAttribute("listPaketData", paketDataList);
            return "addPaketData";
        } else {
            paketDataService.savePaketData(paketData);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/admin/paketdata";
        }

    }

    @GetMapping("/delete")
    public String deleteTiket(@RequestParam("id") Long id) {
        paketDataService.deletePaketData(id);
        return "redirect:/admin/paketdata";
    }
}
