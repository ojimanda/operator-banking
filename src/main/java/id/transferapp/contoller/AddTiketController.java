package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.TiketPesawat;
import id.transferapp.service.admin.TiketPesawatService;

@Controller
@RequestMapping("/admin/tiket")
public class AddTiketController {

    @Autowired
    TiketPesawatService tiketPesawatService;

    @GetMapping
    public String homeAddTiket(Model model) {
        List<TiketPesawat> tikets = tiketPesawatService.getAllTiketPesawat();
        model.addAttribute("addTiket", new TiketPesawat());
        model.addAttribute("listTiket", tikets);
        return "addTiket";
    }

    @PostMapping
    public String addTiket(@ModelAttribute("addTiket") TiketPesawat tiketPesawat, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        List<TiketPesawat> tikets = tiketPesawatService.getAllTiketPesawat();
        if (result.hasErrors()) {
            model.addAttribute("listTiket", tikets);
            return "addTiket";
        } else {
            tiketPesawatService.addTiketPesawat(tiketPesawat);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/admin/tiket";
        }

    }

    @GetMapping("/delete")
    public String deleteTiket(@RequestParam("id") Long id) {
        tiketPesawatService.deleteTiketPesawat(id);
        return "redirect:/admin/tiket";
    }
}
