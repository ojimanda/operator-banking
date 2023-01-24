package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addTiket(@ModelAttribute("addTiket") TiketPesawat tiketPesawat) {
        tiketPesawatService.addTiketPesawat(tiketPesawat);
        return "redirect:/admin/tiket";
    }
}
